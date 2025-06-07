package com.br.karen.composeshoes.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.karen.composeshoes.R
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun ProductContainer(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.background)
            .width(168.dp)
            .height(235.dp)
    ) {
        Image(
            modifier = Modifier
                .width(168.dp)
                .height(180.dp),
            painter = painterResource(R.drawable.image_tenis_teste),
            contentDescription = ""
        )
        Text(
            text = "Chuteira Nike Tiempo 10",
            fontSize = 10.sp,
            letterSpacing = 0.2.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = "R$ 245,99",
            fontSize = 14.sp,
            letterSpacing = 0.2.sp,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
private fun ProductContainerPreview() {
    ComposeShoesTheme {
        ProductContainer()
    }
}