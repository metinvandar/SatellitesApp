package com.metinvandar.satellitesapp.data.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.metinvandar.satellitesapp.data.db.dao.SatelliteDao
import com.metinvandar.satellitesapp.data.db.entity.SatelliteEntity

@Database(entities = [SatelliteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun satelliteDao(): SatelliteDao
}
