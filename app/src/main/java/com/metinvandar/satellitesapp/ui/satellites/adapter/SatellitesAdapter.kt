package com.metinvandar.satellitesapp.ui.satellites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.metinvandar.satellitesapp.databinding.SatelliteItemBinding
import com.metinvandar.satellitesapp.domain.model.Satellite

class SatellitesAdapter: ListAdapter<Satellite, SatelliteItemViewHolder>(ItemDiffCallback()) {

    override fun onBindViewHolder(holder: SatelliteItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteItemViewHolder {
        val binding = SatelliteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SatelliteItemViewHolder(binding)
    }

    private class ItemDiffCallback: DiffUtil.ItemCallback<Satellite>() {
        override fun areItemsTheSame(oldItem: Satellite, newItem: Satellite): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Satellite, newItem: Satellite): Boolean {
            return oldItem.name == newItem.name && oldItem.status == newItem.status
        }
    }
}
