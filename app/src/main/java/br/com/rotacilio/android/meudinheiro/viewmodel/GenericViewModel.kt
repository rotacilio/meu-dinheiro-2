package br.com.rotacilio.android.meudinheiro.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class GenericViewModel : ViewModel() {

    sealed interface UiState {
        object Empty : UiState
        data class Loading(val show: Boolean): UiState
        data class Error(val errorMessage: String?): UiState
    }

    private val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState: StateFlow<UiState>
        get() = _uiState.asStateFlow()

    protected fun setUiState(state: UiState) {
        _uiState.value = state
    }
}