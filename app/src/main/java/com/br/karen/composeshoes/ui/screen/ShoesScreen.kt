package com.br.karen.composeshoes.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.br.karen.composeshoes.ui.components.BottomAppBar
import com.br.karen.composeshoes.ui.model.mockBottomAppBarItems
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun ShoesScreen(modifier: Modifier = Modifier) {
    ComposeShoesTheme {
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    items = mockBottomAppBarItems,
                    item = mockBottomAppBarItems[0]
                )
            }
        ) {
            Box(Modifier.padding(it)) { }
        }
    }
}

@Preview
@Composable
private fun ShoesScreenPreview() {
    ShoesScreen()
}