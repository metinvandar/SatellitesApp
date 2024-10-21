package com.metinvandar.satellitesapp.common.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {

    val main: CoroutineDispatcher

    val io: CoroutineDispatcher

    val db: CoroutineDispatcher

    val computation: CoroutineDispatcher
}
