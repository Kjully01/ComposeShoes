package com.br.karen.composeshoes.di.modules

import com.br.karen.composeshoes.ui.viewmodel.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AppViewModel)
}

