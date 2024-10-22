package com.metinvandar.satellitesapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SatelliteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val active: Boolean,
    val name: String
)
