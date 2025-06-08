package com.br.karen.composeshoes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.br.karen.composeshoes.ui.screen.ShoesScreen
import com.br.karen.composeshoes.ui.theme.ComposeShoesTheme
import com.br.karen.composeshoes.ui.viewmodel.AppViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeShoesTheme {

                val viewModel = koinViewModel<AppViewModel>()
                val uiState by viewModel.uiState.collectAsState()

                ShoesScreen(
                    uiState = uiState,
                    onIntent = viewModel::onIntent
                )
            }
        }
    }
}