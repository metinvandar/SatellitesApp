package com.metinvandar.satellitesapp.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringDef
import androidx.annotation.StringRes
import com.metinvandar.satellitesapp.R

enum class SatelliteStatus(
    private val value: Boolean,
    @StringRes val statusTextRes: Int,
    @DrawableRes val statusIconBackgroundRes: Int,
    @ColorRes val textColorRes: Int,
) {
    ACTIVE(
        value = true,
        statusTextRes =  R.string.satellites_active_status,
        statusIconBackgroundRes = R.drawable.satellite_active_dot_background,
        textColorRes = R.color.color_satellite_active
    ),
    PASSIVE(
        value = false,
        statusTextRes =  R.string.satellites_passive_status,
        statusIconBackgroundRes = R.drawable.satellite_passive_dot_background,
        textColorRes = R.color.color_satellite_passive
    );

    companion object {
        fun fromValue(value: Boolean) = entries.firstOrNull { it.value == value }
    }
}
