package com.metinvandar.satellitesapp.data.remote

import com.metinvandar.satellitesapp.data.remote.model.SatelliteData

interface SatelliteService {
    suspend fun getSatellites(): List<SatelliteData>
}
