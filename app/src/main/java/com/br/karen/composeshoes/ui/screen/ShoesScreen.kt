package com.br.karen.composeshoes.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.br.karen.composeshoes.ui.components.BottomAppBar
import com.br.karen.composeshoes.model.mockBottomAppBarItems
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
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)) {
                Text(
                    modifier = Modifier.align(Alignment.TopCenter),
                    text = "Olá, Cleyton",
                    fontSize = 14.sp,
                    letterSpacing = 0.2.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Preview
@Composable
private fun ShoesScreenPreview() {
    ShoesScreen()
}