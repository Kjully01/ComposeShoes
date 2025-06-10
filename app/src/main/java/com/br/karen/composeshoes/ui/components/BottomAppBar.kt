package com.br.karen.composeshoes.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.model.bottomNavItems
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier,
    items: List<BottomAppBarItem>,
    item: BottomAppBarItem,
    onItemChange: (BottomAppBarItem) -> Unit
) {
    val color = MaterialTheme.colorScheme.outline
    NavigationBar(
        modifier = modifier.drawBehind {
            val strokeWidth = 1.dp.toPx()
            drawLine(
                color = color,
                start = Offset(0f, 0f),
                end = Offset(size.width, 0f),
                strokeWidth = strokeWidth
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        items.forEach {
            val label = it.label
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(it.icon),
                        modifier = Modifier.size(24.dp),
                        contentDescription = "label_$label"
                    )
                },
                label = {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                selected = item.label == label,
                onClick = {
                    onItemChange(it)
                },
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
        BottomAppBar(items = bottomNavItems, item = bottomNavItems[0], onItemChange = {})
    }

}