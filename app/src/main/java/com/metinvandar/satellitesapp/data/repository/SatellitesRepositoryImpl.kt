package com.metinvandar.satellitesapp.data.repository

import com.metinvandar.satellitesapp.common.Result
import com.metinvandar.satellitesapp.common.dispatchers.DispatcherProvider
import com.metinvandar.satellitesapp.data.exception.SatellitesNotFoundException
import com.metinvandar.satellitesapp.data.service.SatelliteService
import com.metinvandar.satellitesapp.data.service.model.SatelliteData
import com.metinvandar.satellitesapp.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SatellitesRepositoryImpl(
    private val satelliteService: SatelliteService,
    private val dispatcher: DispatcherProvider
) : SatelliteRepository {

    override suspend fun getSatellites(): Flow<Result<List<SatelliteData>>> = flow {
        val data = satelliteService.getSatellites()
        if (data.isEmpty())
            emit(Result.Error(SatellitesNotFoundException()))

        emit(Result.Success(satelliteService.getSatellites()))
    }.flowOn(dispatcher.io)

    override suspend fun searchSatellites(query: String): Flow<Result<List<SatelliteData>>> = flow {
        val data = satelliteService.searchSatellites(query)
        if (data.isEmpty())
            emit(Result.Error(SatellitesNotFoundException()))

        emit(Result.Success(satelliteService.searchSatellites(query)))
    }.flowOn(dispatcher.io)
}
