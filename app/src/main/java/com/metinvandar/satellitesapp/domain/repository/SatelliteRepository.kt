package com.metinvandar.satellitesapp.domain.repository

import com.metinvandar.satellitesapp.common.Result
import com.metinvandar.satellitesapp.data.service.model.SatelliteData
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {

    suspend fun getSatellites(): Flow<Result<List<SatelliteData>>>

    suspend fun searchSatellites(query: String): Flow<Result<List<SatelliteData>>>
}
