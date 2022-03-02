package br.com.rotacilio.android.meudinheiro.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.rotacilio.android.meudinheiro.repository.ICardRepository
import kotlinx.coroutines.launch

class CardsFragmentViewModel(
    private val cardRepository: ICardRepository
) : ViewModel() {

    fun loadData() {
        viewModelScope.launch {
            cardRepository.findAll()
        }
    }

}