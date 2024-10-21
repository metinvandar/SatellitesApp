package com.metinvandar.satellitesapp.domain.usecase

import com.metinvandar.satellitesapp.common.Result
import com.metinvandar.satellitesapp.domain.model.Satellite
import kotlinx.coroutines.flow.Flow

interface GetAllSatellitesUseCase {

    suspend operator fun invoke(): Flow<Result<List<Satellite>>>
}
