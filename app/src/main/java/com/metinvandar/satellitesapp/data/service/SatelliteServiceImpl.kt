package com.metinvandar.satellitesapp.data.service

import android.content.Context
import com.metinvandar.satellitesapp.common.extensions.getObjectFromJson
import com.metinvandar.satellitesapp.common.extensions.safeGet
import com.metinvandar.satellitesapp.data.service.model.SatelliteData

class SatelliteServiceImpl(private val context: Context) : SatelliteService {

    override suspend fun getSatellites(): List<SatelliteData> {
        return context.getObjectFromJson<List<SatelliteData>>(SATELLITES_FILE_NAME)
    }

    override suspend fun searchSatellites(query: String): List<SatelliteData> {
        val satellites = getSatellites()
        return satellites.filter { it.name?.contains(query, ignoreCase = true).safeGet() }
    }

    companion object {
        private const val SATELLITES_FILE_NAME = "satellite-list.json"
    }
}
