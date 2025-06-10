package com.br.karen.composeshoes.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    listProducts: List<Product>,
    searchText: String = "",
    onSearchChange: (String) -> Unit,
    onClickSearch: () -> Unit,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit,
    onClickProduct: (Int) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
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
                    modifier = Modifier.weight(0.85f),
                    textPlaceholder = "Pesquisar",
                    searchText = searchText,
                    onSearchChange = { newText ->
                        onSearchChange(newText)
                    }
                )
                IconButtonCustom(
                    modifier = Modifier.weight(0.15f),
                    icon = rememberVectorPainter(Icons.Default.Search),
                    onClick = {
                        onClickSearch()
                    }
                )
            }

            CategoriesFilter(
                categories = mockCategories,
                selectedCategory = selectedCategory,
                onCategoryChange = { newCategory ->
                    onCategoryChange(newCategory)
                }
            )
            if (listProducts.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    //.chunked(2) divide a lista em sublistas.
                    items(listProducts.chunked(2)) { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(18.dp)
                        ) {
                            ProductContainer(
                                item = rowItems[0],
                                modifier = Modifier.weight(1f),
                                onClick = { onClickProduct(rowItems[0].id) }
                            )
                            if (rowItems.size > 1) {
                                ProductContainer(
                                    item = rowItems[1],
                                    modifier = Modifier.weight(1f),
                                    onClick = { onClickProduct(rowItems[1].id) }
                                )
                            } else {
                                // Container vazio visualmente quando a lista for ímpar
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(IntrinsicSize.Min)
                                ) { }
                            }
                        }

                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nada para exibir em $selectedCategory",
                        fontSize = 20.sp,
                        letterSpacing = 0.2.sp
                    )
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
            onClickSearch = {},
            selectedCategory = mockCategories[0],
            onCategoryChange = {},
            onClickProduct = {}
        )
    }
}

@Preview
@Composable
private fun HomeScreenListEmptyPreview() {
    ComposeShoesTheme {
        HomeScreen(
            listProducts = emptyList(),
            onSearchChange = {},
            onClickSearch = {},
            selectedCategory = mockCategories[0],
            onCategoryChange = {},
            onClickProduct = {}
        )
    }
}