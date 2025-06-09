package com.br.karen.composeshoes.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.repository.AppRepository
import com.br.karen.composeshoes.ui.intent.AppSideEffect
import com.br.karen.composeshoes.ui.intent.AppUiIntent
import com.br.karen.composeshoes.ui.state.AppUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(private val repository: AppRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState get() = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<AppSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun onIntent(intent: AppUiIntent) {
        when (intent) {
            is AppUiIntent.OnTabSelected -> onTabSelected(tabSelected = intent.tabSelected)
            is AppUiIntent.OnFilterSelected -> onFilterSelected(filterSelected = intent.filterSelected)
            is AppUiIntent.FetchProducts -> fetchProducts(filter = intent.filter, category = intent.category)
            is AppUiIntent.SearchChange -> handleTextChange(newText = intent.newText)
        }
    }

    private fun onTabSelected(tabSelected: BottomAppBarItem) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(selectedItemBottomBar = tabSelected)
            }
            _sideEffect.emit(AppSideEffect.Navigate(tabSelected.destination))
        }
    }

    private fun onFilterSelected(filterSelected: String) {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(selectedItemFilter = filterSelected)
            }
        }
    }

    private fun fetchProducts(filter: String, category: String) {
        viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                val products = if(filter.isBlank() && category == "Todos") {
                    repository.getAllProducts()
                } else {
                    repository.getProducts(filter, category)
                }

                _uiState.update { currentUiState ->
                    currentUiState.copy(
                        listProducts = products
                    )
                }

            }
        }
    }

    private fun handleTextChange(newText: String) {
        _uiState.update { currentUiState ->
            currentUiState.copy(
                searchText = newText
            )
        }
    }
}