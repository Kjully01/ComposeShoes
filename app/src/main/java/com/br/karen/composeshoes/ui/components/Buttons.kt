package com.br.karen.composeshoes.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.karen.composeshoes.R
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun IconButtonCustom(
    modifier: Modifier = Modifier,
    isOutline: Boolean = false,
    icon: Painter,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (isOutline) Color.Unspecified else MaterialTheme.colorScheme.primary
            )
            .size(53.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(16.dp)
            ),
        onClick = {
            onClick()
        }
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            tint = if (isOutline) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun TextButtonCustom(modifier: Modifier = Modifier, text: String) {
    Button(
        modifier = modifier.height(53.dp),
        onClick = {},
        shape = RoundedCornerShape(16.dp),
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            letterSpacing = 0.2.sp
        )
    }
}

@Preview
@Composable
private fun IconButtonCustomPreview() {
    ComposeShoesTheme {
        IconButtonCustom(icon = rememberVectorPainter(Icons.Default.Search), onClick = {})
    }
}

@Preview
@Composable
private fun IconButtonCustomOutlinePreview() {
    ComposeShoesTheme {
        IconButtonCustom(
            isOutline = true,
            icon = painterResource(R.drawable.shopping_cart),
            onClick = {})
    }
}

@Preview
@Composable
private fun TextButtonCustomPreview() {
    ComposeShoesTheme {
        TextButtonCustom(text = "Adicionar no Carrinho")
    }
}