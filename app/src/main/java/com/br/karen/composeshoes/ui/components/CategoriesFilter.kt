package com.br.karen.composeshoes.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.karen.composeshoes.model.mockCategories
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun CategoriesFilter(modifier: Modifier = Modifier, categorias: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categorias) { categoria ->
            FilterChip(
                selected = categoria == categorias[0],
                onClick = { },
                label = { Text(categoria) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
                    selectedContainerColor = MaterialTheme.colorScheme.primary,

                ),
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CategoriesFilterPreview() {
    ComposeShoesTheme {
        CategoriesFilter(categorias = mockCategories)
    }
}