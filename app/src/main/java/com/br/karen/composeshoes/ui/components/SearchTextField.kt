package com.br.karen.composeshoes.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    textLabel: String,
    textPlaceholder: String = "",
    singleLine: Boolean = true,
    maxLines: Int = 1,
    searchText: String,
    onSearchChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = searchText,
        onValueChange = {
            onSearchChange(it)
        },
        shape = RoundedCornerShape(16.dp),
        label = { Text(text = textLabel) },
        singleLine = singleLine,
        maxLines = maxLines,
        placeholder = { Text(text = textPlaceholder) },
    )
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    ComposeShoesTheme {
        SearchTextField(
            textLabel = "Pesquisar",
            textPlaceholder = "O que você procura?",
            searchText = "",
            onSearchChange = { }
        )
    }
}