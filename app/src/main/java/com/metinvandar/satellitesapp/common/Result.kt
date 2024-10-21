package com.metinvandar.satellitesapp.common

import com.metinvandar.satellitesapp.data.exception.BaseException

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: BaseException) : Result<Nothing>()
}
