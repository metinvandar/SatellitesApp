package com.metinvandar.satellitesapp.data.exception

import com.metinvandar.satellitesapp.R

open class BaseException(exception: Throwable? = null): Exception(exception) {

    open fun getErrorMessageResId(): Int = R.string.error_default_message
}
