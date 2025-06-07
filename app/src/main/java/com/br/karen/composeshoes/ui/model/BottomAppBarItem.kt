package com.br.karen.composeshoes.ui.model

import androidx.annotation.DrawableRes
import com.br.karen.composeshoes.R

open class BottomAppBarItem (
    val label: String,
    @DrawableRes val icon: Int
) {
    object Home: BottomAppBarItem(
        label = "Home",
        icon = R.drawable.home
    )

    object ShoppingCart: BottomAppBarItem(
        label = "Carrinho",
        icon = R.drawable.shopping_cart
    )

    object Profile: BottomAppBarItem(
        label = "Perfil",
        icon = R.drawable.user
    )
}
