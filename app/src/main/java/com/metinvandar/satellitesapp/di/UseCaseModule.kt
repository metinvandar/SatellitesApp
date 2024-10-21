package com.metinvandar.satellitesapp.di

import com.metinvandar.satellitesapp.data.service.model.SatelliteData
import com.metinvandar.satellitesapp.domain.mapper.Mapper
import com.metinvandar.satellitesapp.domain.mapper.SatelliteMapper
import com.metinvandar.satellitesapp.domain.model.Satellite
import com.metinvandar.satellitesapp.domain.repository.SatelliteRepository
import com.metinvandar.satellitesapp.domain.usecase.GetAllSatellitesUseCase
import com.metinvandar.satellitesapp.domain.usecase.GetAllSatellitesUseCaseImpl
import com.metinvandar.satellitesapp.domain.usecase.SearchSatellitesUseCase
import com.metinvandar.satellitesapp.domain.usecase.SearchSatellitesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetAllSatellitesUseCase(
        repository: SatelliteRepository,
        mapper: Mapper<List<SatelliteData>, List<Satellite>>
    ): GetAllSatellitesUseCase {
        return GetAllSatellitesUseCaseImpl(repository, mapper)
    }
    @Provides
    @ViewModelScoped
    fun provideSearchSatelliteUseCase(
        repository: SatelliteRepository,
        mapper: Mapper<List<SatelliteData>, List<Satellite>>
    ): SearchSatellitesUseCase {
        return SearchSatellitesUseCaseImpl(repository, mapper)
    }

    @Provides
    @ViewModelScoped
    fun provideSatelliteMapper(): Mapper<List<SatelliteData>, List<Satellite>> {
        return SatelliteMapper()
    }
}
