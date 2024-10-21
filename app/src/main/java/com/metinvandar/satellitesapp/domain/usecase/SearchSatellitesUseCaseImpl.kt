package com.metinvandar.satellitesapp.domain.usecase

import com.metinvandar.satellitesapp.common.Result
import com.metinvandar.satellitesapp.data.service.model.SatelliteData
import com.metinvandar.satellitesapp.domain.mapper.Mapper
import com.metinvandar.satellitesapp.domain.model.Satellite
import com.metinvandar.satellitesapp.domain.repository.SatelliteRepository
import emitErrorResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchSatellitesUseCaseImpl(
    private val repository: SatelliteRepository,
    private val mapper: Mapper<List<SatelliteData>, List<Satellite>>
) : SearchSatellitesUseCase {

    override suspend fun invoke(query: String): Flow<Result<List<Satellite>>> {
        val flow = if (query.isEmpty()) {
            repository.getSatellites()
        } else repository.searchSatellites(query)
        return flow
            .map {
                when (it) {
                    is Result.Success -> {
                        Result.Success(mapper.mapFrom(it.data))
                    }

                    is Result.Error -> {
                        Result.Error(it.exception)
                    }

                    is Result.Loading -> it
                }
            }.emitErrorResult()
    }
}
