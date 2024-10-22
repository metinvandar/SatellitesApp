package com.metinvandar.satellitesapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.metinvandar.satellitesapp.data.db.entity.SatelliteEntity

@Dao
interface SatelliteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(satelliteEntity: SatelliteEntity)

    @Query("SELECT * FROM satelliteEntity  where id = :id")
    fun getById(id: Int): SatelliteEntity
}
