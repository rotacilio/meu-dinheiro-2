package br.com.rotacilio.android.meudinheiro.usecases.cards

import br.com.rotacilio.android.meudinheiro.model.Card
import br.com.rotacilio.android.meudinheiro.repository.ICardRepository
import br.com.rotacilio.android.meudinheiro.state.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class GetCardsListUseCase(
    private val cardsRepository: ICardRepository
) {
    suspend operator fun invoke(): Flow<DataState<List<Card>>> = flow {
        emit(DataState.Loading)
        val cards = cardsRepository.findAll()
        emit(DataState.Success(cards))
    }.catch { e ->
        e.printStackTrace()
        emit(DataState.Error(e))
    }
}