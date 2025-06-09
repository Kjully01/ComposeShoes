package com.br.karen.composeshoes.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.br.karen.composeshoes.model.BottomAppBarItem
import com.br.karen.composeshoes.model.mockBottomAppBarItems
import com.br.karen.composeshoes.model.mockListProducts
import com.br.karen.composeshoes.ui.components.BottomAppBar
import com.br.karen.composeshoes.ui.intent.AppSideEffect
import com.br.karen.composeshoes.ui.intent.AppUiIntent
import com.br.karen.composeshoes.ui.state.AppUiState
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AppScreen(
    modifier: Modifier = Modifier,
    uiState: AppUiState,
    onIntent: (AppUiIntent) -> Unit,
    sideEffectFlow: SharedFlow<AppSideEffect>
) {

    val navController = rememberNavController()

    val backStackEntryState by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryState?.destination

    LaunchedEffect(currentDestination) {
        val item = mockBottomAppBarItems.find {
            it.destination == (currentDestination?.route ?: BottomAppBarItem.Home.destination)
        }
        if (item != null && item != uiState.selectedItemBottomBar) {
            onIntent(AppUiIntent.OnTabSelected(item))
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        sideEffectFlow
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { effect ->
                when (effect) {
                    is AppSideEffect.Navigate -> {
                        navController.navigate(effect.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
    }

    LaunchedEffect(Unit) {
        onIntent(AppUiIntent.FetchProducts(""))
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                items = mockBottomAppBarItems,
                item = uiState.selectedItemBottomBar,
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
                composable(BottomAppBarItem.Home.destination) {
                    HomeScreen(
                        listProducts = uiState.listProducts,
                        searchText = uiState.searchText,
                        onSearchChange = { newText ->
                            onIntent(AppUiIntent.SearchChange(newText))
                        },
                        onClickSearch = {
                            onIntent(
                                AppUiIntent.FetchProducts(
                                    filter = uiState.searchText,
                                    category = uiState.selectedItemFilter
                                )
                            )
                        },
                        selectedCategory = uiState.selectedItemFilter,
                        onCategoryChange = { selectedItem ->
                            onIntent(AppUiIntent.OnFilterSelected(selectedItem))
                            onIntent(
                                AppUiIntent.FetchProducts(
                                    filter = uiState.searchText,
                                    category = selectedItem
                                )
                            )
                        }
                    )
                }
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
        val sideEffectFlow = MutableSharedFlow<AppSideEffect>(replay = 0)
        AppScreen(
            uiState = AppUiState(listProducts = mockListProducts),
            onIntent = {},
            sideEffectFlow = sideEffectFlow
        )
    }
}