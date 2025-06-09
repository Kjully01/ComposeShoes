package com.br.karen.composeshoes.ui.intent

sealed class AppSideEffect {
    data class Navigate(val route: String) : AppSideEffect()
}