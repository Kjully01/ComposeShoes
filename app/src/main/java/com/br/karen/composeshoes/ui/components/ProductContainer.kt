package com.br.karen.composeshoes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.karen.composeshoes.model.Product
import com.br.karen.composeshoes.model.mockListProducts
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun ProductContainer(
    modifier: Modifier = Modifier,
    item: Product,
    onClick: (Product) -> Unit
) {
    val rippleTheme = rememberRipple(bounded = true)
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
            .height(235.dp)
            .clickable(
                onClick = { onClick(item) },
                indication = rippleTheme,
                interactionSource = interactionSource
            )
    ) {
        if (item.image != null) {
            Image(
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = item.image),
                contentDescription = ""
            )
        } else {
            Box( modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = item.name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = "R$ ${item.price}",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun ProductContainerPreview() {
    ComposeShoesTheme {
        ProductContainer(
            item = mockListProducts[0],
            onClick = {}
        )
    }
}