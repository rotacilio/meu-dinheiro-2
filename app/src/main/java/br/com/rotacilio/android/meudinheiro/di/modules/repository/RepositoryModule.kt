package br.com.rotacilio.android.meudinheiro.di.modules.repository

import br.com.rotacilio.android.meudinheiro.api.services.CardsService
import br.com.rotacilio.android.meudinheiro.repository.ICardRepository
import br.com.rotacilio.android.meudinheiro.repository.impl.CardRepositoryImpl
import org.koin.dsl.module

val repoModule = module {
    single { provideCardRepository(get()) }
}

private fun provideCardRepository(cardsService: CardsService): ICardRepository =
    CardRepositoryImpl(cardsService)
