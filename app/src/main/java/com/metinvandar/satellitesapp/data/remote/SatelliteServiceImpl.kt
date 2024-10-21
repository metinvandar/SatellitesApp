package com.metinvandar.satellitesapp.data.remote

import android.content.Context
import com.metinvandar.satellitesapp.common.extensions.getObjectFromJson
import com.metinvandar.satellitesapp.data.remote.model.SatelliteData

class SatelliteServiceImpl(private val context: Context) : SatelliteService {

    override suspend fun getSatellites(): List<SatelliteData> {
        return context.getObjectFromJson<List<SatelliteData>>(SATELLITES_FILE_NAME)
    }

    companion object {
        private const val SATELLITES_FILE_NAME = "satellite-list.json"
    }
}
