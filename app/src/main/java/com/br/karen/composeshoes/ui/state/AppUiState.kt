package com.br.karen.composeshoes.ui.state

import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.model.Product

data class AppUiState(
    val selectedItem: BottomAppBarItem = BottomAppBarItem.Home,
    val listProducts: List<Product> = emptyList(),
    val searchText: String = ""
)