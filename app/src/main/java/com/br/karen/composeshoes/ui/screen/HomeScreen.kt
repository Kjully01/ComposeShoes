package com.br.karen.composeshoes.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.karen.composeshoes.model.Product
import com.br.karen.composeshoes.model.mockCategories
import com.br.karen.composeshoes.model.mockListProducts
import com.br.karen.composeshoes.ui.components.CategoriesFilter
import com.br.karen.composeshoes.ui.components.IconButtonCustom
import com.br.karen.composeshoes.ui.components.ProductContainer
import com.br.karen.composeshoes.ui.components.SearchTextField
import com.br.karen.composeshoes.ui.intent.AppUiIntent
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    listProducts: List<Product>,
    searchText: String = "",
    onSearchChange: (String) -> Unit,
    onClickSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Olá, Cleyton",
                fontSize = 14.sp,
                letterSpacing = 0.2.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SearchTextField(
                    modifier = Modifier.weight(1f),
                    textLabel = "Pesquisar",
                    textPlaceholder = "O que você procura?",
                    searchText = searchText,
                    onSearchChange = { newText ->
                        onSearchChange(newText)
                    }
                )
                IconButtonCustom(
                    icon = rememberVectorPainter(Icons.Default.Search),
                    onClick = {
                        onClickSearch()
                    }
                )
            }

            CategoriesFilter(categorias = mockCategories)

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                items(listProducts) { item ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ProductContainer(item = item)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    ComposeShoesTheme {
        HomeScreen(
            listProducts = mockListProducts,
            onSearchChange = {},
            onClickSearch = {}
        )
    }
}