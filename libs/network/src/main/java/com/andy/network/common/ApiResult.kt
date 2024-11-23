package com.andy.network.common

import com.andy.network.data.ApiResult

private const val ERROR_CODE_UNAUTHORIZED_CODE = 401
private const val ERROR_CODE_RATE_LIMIT_EXCEEDED = 403
private const val ERROR_CODE_NOT_FOUND = 404
private const val ERROR_CODE_REACHED_API_LIMIT = 422
private const val ERROR_CODE_TOO_MANY_REQUESTS = 429


fun <T> ApiResult.Error<T>.errorMessage(): String {
    val result = if (message.isNullOrEmpty()) {
        "Unknown error!"
    } else {
        message
    }
    return when(code) {
        ERROR_CODE_UNAUTHORIZED_CODE -> {
            "Unauthorized!"
        }

        ERROR_CODE_RATE_LIMIT_EXCEEDED -> {
            "Rate limit exceeded!"
        }

        ERROR_CODE_NOT_FOUND -> {
            "Not found!"
        }

        ERROR_CODE_REACHED_API_LIMIT -> {
            "Only the first 1000 search results are available!"
        }

        ERROR_CODE_TOO_MANY_REQUESTS -> {
            "Too many requests!"
        }

        // TODO still much more error code to implement here!

        else-> {
            result
        }
    }
}