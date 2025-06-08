package com.br.karen.composeshoes.model

import androidx.annotation.DrawableRes
import com.br.karen.composeshoes.R

open class BottomAppBarItem (
    val label: String,
    @DrawableRes val icon: Int,
    val destination: String
) {
    object Home: BottomAppBarItem(
        label = "Home",
        icon = R.drawable.home,
        destination = "home"
    )

    object ShoppingCart: BottomAppBarItem(
        label = "Carrinho",
        icon = R.drawable.shopping_cart,
        destination = "shopping"
    )

    object Profile: BottomAppBarItem(
        label = "Perfil",
        icon = R.drawable.user,
        destination = "profile"
    )
}
