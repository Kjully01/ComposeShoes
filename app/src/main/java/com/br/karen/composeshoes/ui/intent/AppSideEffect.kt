package com.br.karen.composeshoes.ui.intent

import com.br.karen.composeshoes.navigation.AppDestination

sealed class AppSideEffect {
    data class Navigate(val route: AppDestination) : AppSideEffect()
}