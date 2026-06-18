package com.andy.network.common

import androidx.annotation.StringRes
import com.andy.network.R
import com.andy.network.data.ApiResult

private const val ERROR_CODE_UNAUTHORIZED_CODE = 401
private const val ERROR_CODE_RATE_LIMIT_EXCEEDED = 403
private const val ERROR_CODE_NOT_FOUND = 404
private const val ERROR_CODE_REACHED_API_LIMIT = 422
private const val ERROR_CODE_TOO_MANY_REQUESTS = 429

/**
 * Maps an HTTP error [code] to a string resource id. Returns null for codes
 * that have no dedicated message, so the caller can fall back to the raw
 * server message. Only the resource id crosses layers here — the actual
 * user-facing text lives in resources and is resolved at the UI layer.
 */
@StringRes
fun ApiResult.Error<*>.messageResId(): Int? = when (code) {
    ERROR_CODE_UNAUTHORIZED_CODE -> R.string.error_unauthorized
    ERROR_CODE_RATE_LIMIT_EXCEEDED -> R.string.error_rate_limit
    ERROR_CODE_NOT_FOUND -> R.string.error_not_found
    ERROR_CODE_REACHED_API_LIMIT -> R.string.error_api_limit
    ERROR_CODE_TOO_MANY_REQUESTS -> R.string.error_too_many_requests
    else -> null
}

/**
 * Carries an HTTP error across a channel that only exposes a [Throwable]
 * (e.g. Paging's LoadResult.Error). Holds the resolved [messageResId] and/or
 * the raw server [message] so the UI can render a localized string.
 */
class ApiException(
    @StringRes val messageResId: Int? = null,
    override val message: String? = null,
) : Exception(message)
