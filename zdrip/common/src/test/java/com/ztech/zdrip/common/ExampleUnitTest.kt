package com.ztech.zdrip.common

import android.util.Log
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun currentTimeMillis_returns_systemTime() {
        val systemTimeProviderTime = SystemTimeProvider().currentTimeMillis()
        val systemTime = System.currentTimeMillis()

        try{
            assertEquals(systemTimeProviderTime, systemTime)
        }
        catch (e: AssertionError) {
            //consecutive calls to System.currentTimeMillis() can potentially be off by a few milliseconds
            assertTrue( Math.abs(systemTimeProviderTime-systemTime)<10);
        }
    }
}