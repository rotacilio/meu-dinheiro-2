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

    private val _cardsList = MutableLiveData<List<Card>>(listOf())
    val cardsList: LiveData<List<Card>>
        get() = _cardsList

    private fun loadData() {
        viewModelScope.launch {
            _cardsList.postValue(cardRepository.findAll())
        }
    }

    init {
        loadData()
    }
}