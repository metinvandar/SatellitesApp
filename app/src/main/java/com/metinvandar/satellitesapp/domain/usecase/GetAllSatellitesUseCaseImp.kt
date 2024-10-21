package com.metinvandar.satellitesapp.domain.usecase

import com.metinvandar.satellitesapp.common.Result
import com.metinvandar.satellitesapp.data.exception.BaseException
import com.metinvandar.satellitesapp.data.remote.model.SatelliteData
import com.metinvandar.satellitesapp.domain.mapper.Mapper
import com.metinvandar.satellitesapp.domain.model.Satellite
import com.metinvandar.satellitesapp.domain.repository.SatelliteRepository
import emitErrorResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllSatellitesUseCaseImpl(
    private val repository: SatelliteRepository,
    private val mapper: Mapper<List<SatelliteData>, List<Satellite>>
) : GetAllSatellitesUseCase {

    override suspend fun invoke(): Flow<Result<List<Satellite>>> {
        return repository.getSatellites()
            .map {
                when (it) {
                    is Result.Success -> {
                        Result.Success(mapper.mapFrom(it.data))
                    }

                    is Result.Error -> {
                        Result.Error(BaseException())
                    }

                    is Result.Loading -> it
                }
            }.emitErrorResult()
    }
}
