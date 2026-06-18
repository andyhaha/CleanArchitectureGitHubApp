package com.andy.common

import androidx.annotation.StringRes

/**
 * Generic three-state UI contract shared by all feature ViewModels.
 *
 * Replaces the per-screen sealed interfaces that each duplicated the
 * Loading / Success / Error shape. Screen-specific data is carried in
 * the [Success.data] type parameter; combination/business logic stays
 * in the owning ViewModel.
 *
 * [Error] carries either a raw [message] (e.g. a server/exception
 * message) or a [messageResId] for a localized string. The UI layer
 * resolves [messageResId] via stringResource and falls back to
 * [message] — keeping user-facing text out of ViewModels.
 */
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(
        val message: String? = null,
        @StringRes val messageResId: Int? = null,
    ) : UiState<Nothing>
}
