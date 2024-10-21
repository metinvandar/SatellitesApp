package com.metinvandar.satellitesapp.common.extensions

fun String?.safeGet(): String {
    return this ?: ""
}

fun Int?.safeGet(): Int {
    return this ?: 0
}

fun Boolean?.safeGet(): Boolean {
    return this ?: false
}
