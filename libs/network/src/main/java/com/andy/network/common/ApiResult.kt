package com.andy.network.common

import com.andy.network.data.ApiResult

fun <T> ApiResult.Error<T>.errorMessage(): String? {
    val result = if (message.isNullOrEmpty()) {
        "Unknown error"
    } else {
        message
    }
    return if (code == 403) {
        "Rate limit exceeded"
    } else {
        result
    }
}