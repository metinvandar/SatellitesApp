package com.metinvandar.satellitesapp.common.dispatchers

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DispatcherProviderImpl @Inject constructor() : DispatcherProvider {

    override val main = Dispatchers.Main

    override val io = Dispatchers.IO

    override val db = Dispatchers.IO

    override val computation = Dispatchers.Default
}
