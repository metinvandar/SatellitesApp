package com.metinvandar.satellitesapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class SatelliteData(
    @SerializedName("id") val id: Int?,
    @SerializedName("active") val active: Boolean?,
    @SerializedName("name") val name: String?
)
