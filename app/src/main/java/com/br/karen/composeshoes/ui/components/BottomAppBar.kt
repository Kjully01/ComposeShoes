package com.br.karen.composeshoes.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.model.mockBottomAppBarItems
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    items: List<BottomAppBarItem>,
    item: BottomAppBarItem
) {
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEach {
            val label = it.label
            NavigationBarItem(
                icon = { Icon(painter = painterResource(it.icon), modifier = Modifier.size(24.dp), contentDescription = "label_$label") },
                label = { Text(text = label) },
                selected = item.label == label,
                onClick = {},
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = MaterialTheme.colorScheme.inverseOnSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.inverseOnSurface
                )
            )
        }
    }
}

@Preview
@Composable
private fun BottomAppBarPreview() {
    ComposeShoesTheme {
        BottomAppBar(items = mockBottomAppBarItems, item = mockBottomAppBarItems[0])
    }

}