package br.com.rotacilio.android.meudinheiro.di.modules

import android.content.Context
import br.com.rotacilio.android.meudinheiro.di.modules.api.apiModule
import br.com.rotacilio.android.meudinheiro.di.modules.repository.repoModule
import br.com.rotacilio.android.meudinheiro.di.modules.service.apiServicesModule
import br.com.rotacilio.android.meudinheiro.di.modules.viewmodel.viewModelModules
import br.com.rotacilio.android.meudinheiro.utils.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule = module {
//     provideNetworkHelper(context = androidContext())
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

val modules = arrayListOf<Module>().apply {
    add(appModule)
    add(apiModule)
    add(apiServicesModule)
    add(repoModule)
    add(viewModelModules)
}