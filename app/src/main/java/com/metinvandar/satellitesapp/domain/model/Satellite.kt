package com.metinvandar.satellitesapp.domain.model

data class Satellite(
    val id: Int,
    val status: SatelliteStatus,
    val name: String,
    val hasDivider: Boolean
)
