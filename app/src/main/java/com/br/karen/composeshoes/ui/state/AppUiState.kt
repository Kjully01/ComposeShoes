package com.br.karen.composeshoes.ui.state

import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.model.Product
import com.br.karen.composeshoes.model.mockCategories

data class AppUiState(
    val selectedItemBottomBar: BottomAppBarItem = BottomAppBarItem.Home,
    val listProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val selectedItemFilter: String = mockCategories[0]
)