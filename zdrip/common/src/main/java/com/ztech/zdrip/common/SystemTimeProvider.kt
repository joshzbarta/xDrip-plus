package com.ztech.zdrip.common

class SystemTimeProvider : ITimeProvider {
    override fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }
}