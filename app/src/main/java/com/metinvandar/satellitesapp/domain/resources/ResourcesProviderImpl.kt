package com.metinvandar.satellitesapp.domain.resources

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourcesProviderImpl @Inject constructor(@ApplicationContext private val  context: Context): ResourceProvider {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }

    override fun getString(resId: Int, vararg args: Any): String {
        return context.getString(resId, *args)
    }

    override fun getDrawable(resId: Int): Drawable {
        return ContextCompat.getDrawable(context, resId) as Drawable
    }
}
