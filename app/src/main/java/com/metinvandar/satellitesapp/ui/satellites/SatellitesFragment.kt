package com.metinvandar.satellitesapp.ui.satellites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.metinvandar.satellitesapp.databinding.FragmentSatellitesBinding
import com.metinvandar.satellitesapp.domain.model.Satellite
import com.metinvandar.satellitesapp.domain.model.SatelliteStatus
import com.metinvandar.satellitesapp.ui.satellites.adapter.SatellitesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SatellitesFragment: Fragment() {

    private var _binding: FragmentSatellitesBinding? = null
    private val binding get() = _binding!!
    private lateinit var satellitesAdapter: SatellitesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSatellitesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        satellitesAdapter = SatellitesAdapter()
        binding.satellitesRecyclerView.adapter = satellitesAdapter

        val list = listOf(
            Satellite(
                id = 1,
                status = SatelliteStatus.PASSIVE,
                name = "Dragon-1",
                hasDivider = true
            ),
            Satellite(
                id = 2,
                status = SatelliteStatus.ACTIVE,
                name = "Dragon-2",
                hasDivider = true
            ),
            Satellite(
                id = 3,
                status = SatelliteStatus.PASSIVE,
                name = "Dragon-3",
                hasDivider = false
            ),
        )

        satellitesAdapter.submitList(list)
    }
}
