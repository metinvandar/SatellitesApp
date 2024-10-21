package com.metinvandar.satellitesapp.ui.state

import com.metinvandar.satellitesapp.domain.model.Satellite

sealed interface SatelliteState {

    data object Loading : SatelliteState

    data class Failed(val message: String) : SatelliteState

    data class SatellitesLoaded(val satellites: List<Satellite>) : SatelliteState
}
