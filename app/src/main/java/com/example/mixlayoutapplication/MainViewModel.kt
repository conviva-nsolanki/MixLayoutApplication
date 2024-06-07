package com.example.mixlayoutapplication

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mixlayoutapplication.data.NationalPark
import com.example.mixlayoutapplication.services.NationalParkServices
import com.example.mixlayoutapplication.services.response.ParkDetailResponse.Companion.toNationalPark
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UiState(
    val nationalParksCalifornia: List<NationalPark> = listOf(),
    val nationalParksAlaska: List<NationalPark> = listOf()
)
@HiltViewModel
class MainViewModel @Inject constructor(private val services: NationalParkServices): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState
    companion object {
        private const val TAG: String = "MainViewModel"
    }
    fun loadNationalPark(state: String = "CA") {
        viewModelScope.launch {
            try {
                val nationalParks: List<NationalPark> = services.getNationalParkByStateCodes(state)
                    .data.map {
                        it.toNationalPark()
                    }
                _uiState.update {
                    if (state == "CA") {
                        it.copy(
                            nationalParksCalifornia = nationalParks
                        )
                    } else {
                        it.copy(
                            nationalParksAlaska = nationalParks
                        )
                    }
                }
            } catch (e: Exception) {
                e.localizedMessage?.let { Log.d(TAG, it) }?: Log.d(TAG, "Error: $e")
            }
        }
    }


}