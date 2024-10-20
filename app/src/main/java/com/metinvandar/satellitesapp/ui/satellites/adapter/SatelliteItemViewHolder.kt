package com.metinvandar.satellitesapp.ui.satellites.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.metinvandar.satellitesapp.databinding.SatelliteItemBinding
import com.metinvandar.satellitesapp.domain.model.Satellite

class SatelliteItemViewHolder(private val binding: SatelliteItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(satellite: Satellite) {
        val context = binding.root.context
        binding.run {
            satelliteNameTextView.text = satellite.name
            satelliteStatusTextView.text = context.getString(satellite.status.statusTextRes)
            satelliteStatusTextView.setTextColor(context.getColor(satellite.status.textColorRes))
            satelliteNameTextView.setTextColor(context.getColor(satellite.status.textColorRes))
            satelliteStatusContainer.background = ContextCompat.getDrawable(context, satellite.status.statusIconBackgroundRes)
            divider.visibility = if (satellite.hasDivider) View.VISIBLE else View.GONE
        }
    }
}
