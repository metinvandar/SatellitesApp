package com.metinvandar.satellitesapp.di

import android.content.Context
import androidx.room.Room
import com.metinvandar.satellitesapp.R
import com.metinvandar.satellitesapp.common.dispatchers.DispatcherProvider
import com.metinvandar.satellitesapp.common.dispatchers.DispatcherProviderImpl
import com.metinvandar.satellitesapp.data.db.dao.SatelliteDao
import com.metinvandar.satellitesapp.data.db.database.AppDatabase
import com.metinvandar.satellitesapp.data.service.SatelliteService
import com.metinvandar.satellitesapp.data.service.SatelliteServiceImpl
import com.metinvandar.satellitesapp.data.repository.SatellitesRepositoryImpl
import com.metinvandar.satellitesapp.domain.repository.SatelliteRepository
import com.metinvandar.satellitesapp.domain.resources.ResourceProvider
import com.metinvandar.satellitesapp.domain.resources.ResourcesProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSatelliteService(@ApplicationContext context: Context): SatelliteService {
        return SatelliteServiceImpl(context)
    }

    @Singleton
    @Provides
    fun provideSatelliteRepository(
        satelliteService: SatelliteService,
        dispatcherProvider: DispatcherProvider
    ): SatelliteRepository {
        return SatellitesRepositoryImpl(satelliteService, dispatcherProvider)
    }

    @Provides
    @Singleton
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourcesProviderImpl(context)
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProviderImpl()
    }

    @Provides
    @Singleton
    fun provideSatelliteDao(database: AppDatabase): SatelliteDao {
        return database.satelliteDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = context.getString(R.string.app_database)
        ).build()
    }
}
