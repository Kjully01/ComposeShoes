package com.br.karen.composeshoes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.br.karen.composeshoes.ui.intent.AppUiIntent
import com.br.karen.composeshoes.ui.screen.DetailsScreen
import com.br.karen.composeshoes.ui.screen.HomeScreen
import com.br.karen.composeshoes.ui.screen.ProfileScreen
import com.br.karen.composeshoes.ui.screen.ShoppingCartScreen
import com.br.karen.composeshoes.ui.state.AppUiState
import com.br.karen.composeshoes.ui.state.ProductUiState

@Composable
fun AppNavHost(
    navController: NavHostController,
    uiState: AppUiState,
    productUiState: ProductUiState,
    onIntent: (AppUiIntent) -> Unit
) {
    NavHost(navController, startDestination = AppDestination.Home.route) {

        composable(AppDestination.Home.route) {
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
                },
                onClickProduct = { productId ->
                    onIntent(AppUiIntent.OnProductClicked(productId))
                }
            )
        }

        composable(AppDestination.ShoppingCart.route) { ShoppingCartScreen() }

        composable(AppDestination.Profile.route) { ProfileScreen() }

        composable(
            route = AppDestination.ProductDetail.baseRoute,
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: return@composable
            DetailsScreen(
                idProduct = productId,
                loadProduct = { id -> onIntent(AppUiIntent.OnLoadProduct(id)) },
                item = productUiState.product,
                onClick = {
                    onIntent(AppUiIntent.NavigateTo(AppDestination.ShoppingCart))
                }
            )
        }
    }
}