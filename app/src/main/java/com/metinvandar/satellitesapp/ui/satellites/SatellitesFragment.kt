package com.metinvandar.satellitesapp.ui.satellites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.metinvandar.satellitesapp.common.extensions.showError
import com.metinvandar.satellitesapp.databinding.FragmentSatellitesBinding
import com.metinvandar.satellitesapp.domain.model.Satellite
import com.metinvandar.satellitesapp.domain.model.SatelliteStatus
import com.metinvandar.satellitesapp.ui.satellites.adapter.SatellitesAdapter
import com.metinvandar.satellitesapp.ui.state.SatelliteState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SatellitesFragment : Fragment() {

    private var _binding: FragmentSatellitesBinding? = null
    private val binding get() = _binding!!
    private lateinit var satellitesAdapter: SatellitesAdapter

    private val viewModel: SatellitesViewModel by viewModels()

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
        binding.searchLayout.searchEditText.doAfterTextChanged {
            viewModel.searchSatellite(it.toString())
        }
        collectUiState()
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.satellitesState.collect { state ->
                    when(state) {
                        is SatelliteState.Loading -> {
                            binding.progress.visibility = View.VISIBLE
                        }
                        is SatelliteState.SatellitesLoaded -> {
                            binding.progress.visibility = View.GONE
                            satellitesAdapter.submitList(state.satellites)
                        }
                        is SatelliteState.Failed -> {
                            binding.progress.visibility = View.GONE
                            showError(state.message)
                        }
                    }
                }
            }
        }
    }
}
