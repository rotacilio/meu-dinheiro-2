package br.com.rotacilio.android.meudinheiro.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rotacilio.android.meudinheiro.model.Card
import br.com.rotacilio.android.meudinheiro.repository.ICardRepository
import kotlinx.coroutines.launch

class CardsFragmentViewModel(
    private val cardRepository: ICardRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState>
        get() = _viewState

    private fun loadData() {
        _viewState.postValue(ViewState.Loading)
        viewModelScope.launch {
            try {
                val cards = cardRepository.findAll()
                _viewState.postValue(ViewState.CardsLoaded(cards))
            } catch (e: Exception) {
                _viewState.postValue(ViewState.Error(e.localizedMessage))
            }
        }
    }

    init {
        loadData()
    }

    sealed class ViewState {
        object Loading: ViewState()
        data class CardsLoaded(val cards: List<Card>): ViewState()
        data class Error(val errorMessage: String?): ViewState()
    }
}