package com.sangeeta.yawa.data.provider

interface LocationProvider {
    suspend fun getPreferredLocationString(): String
}