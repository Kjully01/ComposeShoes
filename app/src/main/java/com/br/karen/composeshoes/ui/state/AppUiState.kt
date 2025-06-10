package com.br.karen.composeshoes.ui.state

import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.model.EnumCategory
import com.br.karen.composeshoes.model.Product
import com.br.karen.composeshoes.model.bottomNavItems

data class AppUiState(
    val selectedItemBottomBar: BottomAppBarItem = bottomNavItems.first(),
    val listProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val selectedItemFilter: EnumCategory = EnumCategory.entries[0],
    val isShowBottomAppBar: Boolean = true
)