package br.com.rotacilio.android.meudinheiro.di.modules.service

import br.com.rotacilio.android.meudinheiro.api.services.CardsService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiServicesModule = module {
    single { provideCardService(get()) }
}

private fun provideCardService(retrofit: Retrofit) = retrofit.create(CardsService::class.java)