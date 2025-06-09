package com.br.karen.composeshoes.ui.intent

import com.br.karen.composeshoes.model.BottomAppBarItem

sealed class AppUiIntent {
    data class OnTabSelected(val tabSelected: BottomAppBarItem) : AppUiIntent()
    data class FetchProducts(val filter: String = "") : AppUiIntent()
}