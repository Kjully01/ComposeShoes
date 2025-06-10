package com.br.karen.composeshoes.navigation

sealed class AppDestination(val route: String) {
    data object Home : AppDestination("home")
    data object ShoppingCart : AppDestination("shopping")
    data object Profile : AppDestination("profile")
    data class ProductDetail(val productId: String) : AppDestination("productDetail/$productId") {
        companion object {
            const val baseRoute = "productDetail/{productId}"
        }
    }
}