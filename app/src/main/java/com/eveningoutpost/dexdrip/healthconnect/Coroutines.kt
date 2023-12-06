package com.eveningoutpost.dexdrip.healthconnect

import android.annotation.TargetApi

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthPermission
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException
import java.util.function.BiConsumer
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

// jamorham

object Coroutines {
    @JvmOverloads
    fun <R> getContinuation(
        onFinished: BiConsumer<R?, Throwable?>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): Continuation<R> {
        return object : Continuation<R> {
            override val context: CoroutineContext
                get() = dispatcher

            @TargetApi(Build.VERSION_CODES.N)
            override fun resumeWith(result: Result<R>) {
                onFinished.accept(result.getOrNull(), result.exceptionOrNull())
            }
        }
    }

    fun interface FunctionWrapperWithBiConsumer<T, R> {
        fun apply(t: T, resultHandler: BiConsumer<R?, Throwable?>?)
    }

    fun interface FunctionWrapperWithBiConsumer2<T, U, R> {
        fun apply(t: T, u: U, resultHandler: BiConsumer<R?, Throwable?>?)
    }

    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.N)
    @JvmStatic
    // Note: the fact that this anti-pattern is needed shows the imprecision of kotlin and lack of interoperability
    fun <T, R> suspendFunction(fn: (T) -> R): FunctionWrapperWithBiConsumer<T, R> {
        return FunctionWrapperWithBiConsumer { t, resultHandler ->
            if (resultHandler == null) throw RuntimeException("result handler was null!")
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val result = fn(t)
                    resultHandler.accept(result, null)
                } catch (e: Throwable) {
                    resultHandler.accept(null, e)
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.N)
    @JvmStatic
    // Note: the fact that this anti-pattern is needed shows the imprecision of kotlin and lack of interoperability
    fun <T, U, R> suspendFunction(fn: (T, U) -> R): FunctionWrapperWithBiConsumer2<T, U, R> {
        return FunctionWrapperWithBiConsumer2 { t, u,  resultHandler ->
            if (resultHandler == null) throw RuntimeException("result handler was null!")
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val result = fn(t, u)
                    resultHandler.accept(result, null)
                } catch (e: Throwable) {
                    resultHandler.accept(null, e)
                }
            }
        }
    }

    @JvmStatic
    fun getGrantedPermissions(healthConnectClient: HealthConnectClient, permissions: Set<HealthPermission>): Set<HealthPermission> {
        return runBlocking {
            return@runBlocking healthConnectClient.permissionController.getGrantedPermissions(permissions)
        }
    }
}

