package com.andy.common

import com.andy.common.JsonManager.moshi

inline fun<reified T> T.toJson(): String {
    return try {
        val adapter = moshi.adapter(T::class.java)
        return adapter.toJson(this)
    } catch (e: Throwable) {
        ""
    }
}