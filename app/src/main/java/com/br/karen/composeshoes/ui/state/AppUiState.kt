package com.br.karen.composeshoes.ui.state

import com.br.karen.composeshoes.model.BottomAppBarItem

data class AppUiState(
    val selectedItem: BottomAppBarItem = BottomAppBarItem.Home
)