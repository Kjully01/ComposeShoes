package com.br.karen.composeshoes.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.model.mockBottomAppBarItems
import com.br.karen.composeshoes.ui.components.BottomAppBar
import com.br.karen.composeshoes.ui.intent.AppUiIntent
import com.br.karen.composeshoes.ui.state.AppUiState
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme

@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    uiState: AppUiState,
    onIntent: (AppUiIntent) -> Unit
) {

    val navController = rememberNavController()

    val backStackEntryState by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryState?.destination

    LaunchedEffect(currentDestination) {
        val item = mockBottomAppBarItems.find {
            it.destination == (currentDestination?.route ?: BottomAppBarItem.Home.destination)
        }
        if (item != null && item != uiState.selectedItem) {
            onIntent(AppUiIntent.OnTabSelected(item))
        }
    }

    LaunchedEffect(uiState.selectedItem) {
        navController.navigate(uiState.selectedItem.destination) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                items = mockBottomAppBarItems,
                item = uiState.selectedItem,
                onItemChange = { selectedItem ->
                    onIntent(AppUiIntent.OnTabSelected(selectedItem))
                }
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = BottomAppBarItem.Home.destination,
            ) {
                composable(BottomAppBarItem.Home.destination) { HomeScreen() }
                composable(BottomAppBarItem.ShoppingCart.destination) { ShoppingCartScreen() }
                composable(BottomAppBarItem.Profile.destination) { ProfileScreen() }
            }
        }
    }

}

@Preview
@Composable
private fun AppScreenPreview() {
    ComposeShoesTheme {
        AppScreen(
            uiState = AppUiState(),
            onIntent = {}
        )
    }
}