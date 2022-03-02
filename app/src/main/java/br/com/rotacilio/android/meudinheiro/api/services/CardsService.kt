package br.com.rotacilio.android.meudinheiro.api.services

import br.com.rotacilio.android.meudinheiro.model.Card
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CardsService {

    @GET("cards")
    suspend fun findAllCards(): Response<List<Card>>
}