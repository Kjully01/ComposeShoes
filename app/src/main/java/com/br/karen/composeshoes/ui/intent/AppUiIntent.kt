package com.br.karen.composeshoes.ui.intent

import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.model.EnumCategory

sealed class AppUiIntent {
    data class OnTabSelected(val tabSelected: BottomAppBarItem) : AppUiIntent()
    data class OnFilterSelected(val filterSelected: EnumCategory) : AppUiIntent()
    data class FetchProducts(val filter: String = "", val category: EnumCategory = EnumCategory.entries[0]) : AppUiIntent()
    data class SearchChange(val newText: String) : AppUiIntent()
    data class OnProductClicked(val productId: Int) : AppUiIntent()
    data class OnLoadProduct(val productId: Int) : AppUiIntent()
    data class OnRouteChanged(val route: String) : AppUiIntent()
}