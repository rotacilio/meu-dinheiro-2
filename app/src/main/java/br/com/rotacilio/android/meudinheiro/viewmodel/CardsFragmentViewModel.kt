package br.com.rotacilio.android.meudinheiro.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.rotacilio.android.meudinheiro.model.Card
import br.com.rotacilio.android.meudinheiro.state.DataState
import br.com.rotacilio.android.meudinheiro.usecases.cards.GetCardsListUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CardsFragmentViewModel(
    private val getCardsListUseCase: GetCardsListUseCase,
    private val coroutineContext: CoroutineContext
) : GenericViewModel() {

    sealed interface UiStateVC : UiState {
        data class CardsLoaded(val cards: List<Card>) : UiStateVC
    }

    fun loadData() {
        viewModelScope.launch(coroutineContext) {
            getCardsListUseCase().onEach { dataState ->
                when (dataState) {
                    is DataState.Loading -> setUiState(UiState.Loading(true))
                    is DataState.Error -> {
                        setUiState(UiState.Loading(false))
                        setUiState(UiState.Error(dataState.exception.localizedMessage))
                    }
                    is DataState.Success -> {
                        setUiState(UiState.Loading(false))
                        setUiState(UiStateVC.CardsLoaded(dataState.data))
                    }
                }
            }.catch { e ->
                e.printStackTrace()
                setUiState(UiState.Error(e.localizedMessage))
            }.launchIn(viewModelScope)
        }
    }
}