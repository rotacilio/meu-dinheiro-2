package br.com.rotacilio.android.meudinheiro.state

import retrofit2.HttpException

sealed interface DataState<out R> {
    object Loading : DataState<Nothing>
    data class Error(val exception: Throwable) : DataState<Nothing> {
        val statusCode: Int get() = if (this.exception is HttpException) this.exception.code() else -1
    }
    data class Success<out T>(val data: T) : DataState<T>
}
