package com.andy.network.domain

sealed interface Result<out T> {
    data class Success<T>(val value: T) : Result<T>
    data class Failure(val throwable: Throwable? = null) : Result<Nothing>
    class Error(val code: Int, val message: String?) : Result<Nothing>
}