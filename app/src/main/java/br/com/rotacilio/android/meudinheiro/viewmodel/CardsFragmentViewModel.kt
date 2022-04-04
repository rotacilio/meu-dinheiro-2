package br.com.rotacilio.android.meudinheiro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rotacilio.android.meudinheiro.model.Card
import br.com.rotacilio.android.meudinheiro.state.DataState
import br.com.rotacilio.android.meudinheiro.usecases.cards.GetCardsListUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CardsFragmentViewModel(
    private val getCardsListUseCase: GetCardsListUseCase,
    private val coroutineContext: CoroutineContext
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Empty)
    val viewState = _viewState.asStateFlow()

    private fun loadData() {
        viewModelScope.launch(coroutineContext) {
            getCardsListUseCase().onEach { dataState ->
                when (dataState) {
                    is DataState.Loading -> _viewState.value = ViewState.Loading(true)
                    is DataState.Error -> {
                        _viewState.value = ViewState.Loading(false)
                        _viewState.value = ViewState.Error(dataState.exception.localizedMessage)
                    }
                    is DataState.Success -> {
                        _viewState.value = ViewState.Loading(false)
                        _viewState.value = ViewState.CardsLoaded(dataState.data)
                    }
                }
            }.catch { e ->
                e.printStackTrace()
                _viewState.value = ViewState.Error(e.localizedMessage)
            }.launchIn(viewModelScope)
        }
    }

    init {
        loadData()
    }

    sealed interface ViewState {
        object Empty : ViewState
        data class Loading(val show: Boolean): ViewState
        data class CardsLoaded(val cards: List<Card>): ViewState
        data class Error(val errorMessage: String?): ViewState
    }
}