package com.metinvandar.satellitesapp.ui.satellites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metinvandar.satellitesapp.common.Result
import com.metinvandar.satellitesapp.domain.resources.ResourceProvider
import com.metinvandar.satellitesapp.domain.usecase.GetAllSatellitesUseCase
import com.metinvandar.satellitesapp.domain.usecase.SearchSatellitesUseCase
import com.metinvandar.satellitesapp.ui.state.SatelliteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SatellitesViewModel @Inject constructor(
    private val getAllSatellitesUseCase: GetAllSatellitesUseCase,
    private val resourceProvider: ResourceProvider,
    private val searchSatellites: SearchSatellitesUseCase
) : ViewModel() {

    private val _satellitesState = MutableStateFlow<SatelliteState>(SatelliteState.Loading)
    val satellitesState: StateFlow<SatelliteState> = _satellitesState

    private val searchTrigger = MutableStateFlow("")

    init {
        getSatellites()
        collectSearchTrigger()
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

    fun searchSatellite(query: String) {
        viewModelScope.launch {
            searchTrigger.emit(query)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun collectSearchTrigger() {
        viewModelScope.launch {
            searchTrigger.debounce(SEARCH_DELAY)
                .filter { it.length >= MIN_QUERY_LENGTH || it.isEmpty() }
                .flatMapLatest { searchQuery ->
                    searchSatellites(searchQuery)
                }
                .collect { result ->
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

    companion object {
        private const val SEARCH_DELAY = 500L
        private const val MIN_QUERY_LENGTH = 3
    }
}
