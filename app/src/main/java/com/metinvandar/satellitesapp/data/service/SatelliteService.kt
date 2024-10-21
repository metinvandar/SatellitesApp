package com.metinvandar.satellitesapp.data.service

import com.metinvandar.satellitesapp.data.service.model.SatelliteData

interface SatelliteService {
    suspend fun getSatellites(): List<SatelliteData>

    suspend fun searchSatellites(query: String): List<SatelliteData>
}
