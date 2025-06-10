package com.br.karen.composeshoes.model

import androidx.annotation.DrawableRes
import com.br.karen.composeshoes.R
import com.br.karen.composeshoes.navigation.AppDestination

data class BottomAppBarItem(
    val destination: AppDestination,
    val label: String,
    @DrawableRes val icon: Int
)

val bottomNavItems = listOf(
    BottomAppBarItem(AppDestination.Home, "Home", R.drawable.home),
    BottomAppBarItem(AppDestination.ShoppingCart, "Carrinho", R.drawable.shopping_cart),
    BottomAppBarItem(AppDestination.Profile, "Perfil", R.drawable.user)
)