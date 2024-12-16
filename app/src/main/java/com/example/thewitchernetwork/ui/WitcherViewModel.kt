package com.example.thewitchernetwork.ui

import androidx.lifecycle.ViewModel
import com.example.thewitchernetwork.model.Monster
import com.example.thewitchernetwork.model.Witcher
import com.example.thewitchernetwork.model.WitcherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WitcherViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(WitcherUiState())
    val uiState: StateFlow<WitcherUiState> = _uiState.asStateFlow()

    fun updateWitcher(newWitcher: Witcher) {
        _uiState.update { currentState ->
            currentState.copy(
                witcher = newWitcher
            )
        }
    }

    fun updateMonster(newMonster: Monster) {
        _uiState.update { currentState ->
            currentState.copy(
                monster = newMonster
            )
        }
    }

    fun reset() {
        _uiState.value = WitcherUiState()
    }

}