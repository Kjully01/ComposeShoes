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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.br.karen.composeshoes.model.bottomNavItems
import com.br.karen.composeshoes.model.mockListProducts
import com.br.karen.composeshoes.navigation.AppDestination
import com.br.karen.composeshoes.navigation.AppNavHost
import com.br.karen.composeshoes.ui.components.BottomAppBar
import com.br.karen.composeshoes.ui.intent.AppSideEffect
import com.br.karen.composeshoes.ui.intent.AppUiIntent
import com.br.karen.composeshoes.ui.state.AppUiState
import com.br.karen.composeshoes.ui.state.ProductUiState
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Composable
fun AppScreen(
    uiState: AppUiState,
    productUiState: ProductUiState,
    onIntent: (AppUiIntent) -> Unit,
    sideEffectFlow: SharedFlow<AppSideEffect>
) {

    val navController = rememberNavController()

    val backStackEntryState by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntryState?.destination?.route

    LaunchedEffect(currentDestination) {
        val item = bottomNavItems.find {
            it.destination.route == (currentDestination ?: AppDestination.Home.route)
        }
        if (item != null && item != uiState.selectedItemBottomBar) {
            onIntent(AppUiIntent.OnTabSelected(item))
        }
        currentDestination?.let {
            onIntent(AppUiIntent.OnRouteChanged(it))
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        sideEffectFlow
            .flowWithLifecycle(lifecycleOwner.lifecycle)
            .collect { effect ->
                when (effect) {
                    is AppSideEffect.Navigate -> {
                        navController.navigate(effect.route.route) {
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
            if (uiState.isShowBottomAppBar) {
                BottomAppBar(
                    items = bottomNavItems,
                    item = uiState.selectedItemBottomBar,
                    onItemChange = { selectedItem ->
                        onIntent(AppUiIntent.OnTabSelected(selectedItem))
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            AppNavHost(
                navController = navController,
                uiState = uiState,
                productUiState = productUiState,
                onIntent = onIntent
            )
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
            productUiState = ProductUiState(),
            onIntent = {},
            sideEffectFlow = sideEffectFlow
        )
    }
}