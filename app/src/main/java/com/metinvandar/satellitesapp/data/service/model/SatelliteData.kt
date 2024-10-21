package com.metinvandar.satellitesapp.data.service.model

import com.google.gson.annotations.SerializedName

data class SatelliteData(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("active") val active: Boolean? = null,
    @SerializedName("name") val name: String? = null
)
