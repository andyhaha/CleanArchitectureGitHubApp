package com.andy.common

import com.squareup.moshi.Moshi

object JsonManager {
    val moshi = Moshi.Builder()
        .build()

    inline fun<reified T> parse(json: String): T? {
        val adapter = moshi.adapter(T::class.java)
        return adapter.fromJson(json)
    }
}