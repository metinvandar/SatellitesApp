package com.metinvandar.satellitesapp.ui.satellites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metinvandar.satellitesapp.common.Result
import com.metinvandar.satellitesapp.domain.resources.ResourceProvider
import com.metinvandar.satellitesapp.domain.usecase.GetAllSatellitesUseCase
import com.metinvandar.satellitesapp.ui.state.SatelliteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SatellitesViewModel @Inject constructor(
    private val getAllSatellitesUseCase: GetAllSatellitesUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _satellitesState = MutableStateFlow<SatelliteState>(SatelliteState.Loading)
    val satellitesState: StateFlow<SatelliteState> = _satellitesState

    init {
        getSatellites()
    }

    private fun getSatellites() {
        viewModelScope.launch {
            getAllSatellitesUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _satellitesState.value = SatelliteState.SatellitesLoaded(result.data)
                    }

                    is Result.Loading -> {
                        _satellitesState.value = SatelliteState.Loading
                    }

                    is Result.Error -> {
                        _satellitesState.value =
                            SatelliteState.Failed(resourceProvider.getString(result.exception.getErrorMessageResId()))
                    }
                }
            }
        }
    }
}
