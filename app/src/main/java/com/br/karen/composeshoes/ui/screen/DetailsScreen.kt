package com.br.karen.composeshoes.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.karen.composeshoes.R
import com.br.karen.composeshoes.model.Product
import com.br.karen.composeshoes.model.mockListProducts
import com.br.karen.composeshoes.ui.components.IconButtonCustom
import com.br.karen.composeshoes.ui.components.TextButtonCustom
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    idProduct: Int,
    item: Product,
    loadProduct: (Int) -> Unit,
    onClick: () -> Unit
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        loadProduct(idProduct)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 2),
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.image_tenis_teste_2),
            contentDescription = "img_product"
        )
        Column(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Icon(
                        painter = painterResource(R.drawable.heart),
                        contentDescription = "ic_heart"
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (i in 1..4) {
                        Icon(
                            modifier = Modifier.size(14.dp),
                            painter = painterResource(R.drawable.ri_star_fill),
                            contentDescription = "star_fill",
                            tint = Color.Unspecified
                        )
                    }
                    Icon(
                        modifier = Modifier.size(14.dp),
                        painter = painterResource(R.drawable.ri_star_half_fill),
                        contentDescription = "star_half_fill",
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        modifier = Modifier.align(Alignment.Top),
                        text = "(10)",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "R$ ${item.price}",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextButtonCustom(
                modifier = Modifier.weight(0.85f),
                text = "Adicionar no carrinho",
                onClick = {
                    onClick()
                }
            )
            IconButtonCustom(
                modifier = Modifier.weight(0.15f),
                isOutline = true,
                icon = painterResource(R.drawable.shopping_cart),
                onClick = {
                    onClick()
                }
            )
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    ComposeShoesTheme {
        DetailsScreen(
            idProduct = mockListProducts[1].id,
            loadProduct = {},
            item = mockListProducts[1],
            onClick = {}
        )
    }
}