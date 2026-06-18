package com.andy.common

/**
 * Generic three-state UI contract shared by all feature ViewModels.
 *
 * Replaces the per-screen sealed interfaces that each duplicated the
 * Loading / Success / Error shape. Screen-specific data is carried in
 * the [Success.data] type parameter; combination/business logic stays
 * in the owning ViewModel.
 */
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String?) : UiState<Nothing>
}
