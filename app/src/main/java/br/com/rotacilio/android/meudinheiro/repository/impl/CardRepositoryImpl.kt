package br.com.rotacilio.android.meudinheiro.repository.impl

import br.com.rotacilio.android.meudinheiro.api.services.CardsService
import br.com.rotacilio.android.meudinheiro.model.Card
import br.com.rotacilio.android.meudinheiro.repository.ICardRepository
import br.com.rotacilio.android.meudinheiro.repository.ICrudRepository

class CardRepositoryImpl(
    private val cardsApi: CardsService
) : ICardRepository {
    override suspend fun createOrUpdate(data: Card): Card {
        TODO("Not yet implemented")
    }

    override suspend fun create(data: Card): Card {
        TODO("Not yet implemented")
    }

    override suspend fun update(data: Card): Card {
        TODO("Not yet implemented")
    }

    override suspend fun delete(data: Card) {
        TODO("Not yet implemented")
    }

    override suspend fun findById(id: Long): Card? {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Card> {
        val response = cardsApi.findAllCards()
        return if (response.isSuccessful && response.body() != null)
            response.body()!!
        else emptyList()
    }
}