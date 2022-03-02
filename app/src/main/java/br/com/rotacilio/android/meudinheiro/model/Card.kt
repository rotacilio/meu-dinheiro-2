package br.com.rotacilio.android.meudinheiro.model

import com.squareup.moshi.Json
import java.util.*

data class Card(
    val id: Long = 0L,
    val nickname: String,
    @Json(name = "due_day") val dueDay: Int,
    @Json(name = "expiration_date") val expirationDate: String,
    val flag: CardFlag,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String?
)

data class CardFlag(
    val id: Long = 0L,
    val description: String
)