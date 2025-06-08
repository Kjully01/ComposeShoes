package com.br.karen.composeshoes.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.ui.intent.AppUiIntent
import com.br.karen.composeshoes.ui.state.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState get() = _uiState.asStateFlow()

    fun onIntent(intent: AppUiIntent) {
        when (intent) {
            is AppUiIntent.OnTabSelected -> onTabSelected(tabSelected = intent.tabSelected)
        }
    }

    private fun onTabSelected(tabSelected: BottomAppBarItem) {
        _uiState.update { it.copy(selectedItem = tabSelected) }
    }
}