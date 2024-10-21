package com.metinvandar.satellitesapp.domain.resources

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg args: Any): String

    fun getDrawable(@DrawableRes resId: Int): Drawable
}
