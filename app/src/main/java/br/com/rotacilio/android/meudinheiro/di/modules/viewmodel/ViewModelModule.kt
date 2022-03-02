package br.com.rotacilio.android.meudinheiro.di.modules.viewmodel

import br.com.rotacilio.android.meudinheiro.viewmodel.CardsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { CardsFragmentViewModel(get()) }
}