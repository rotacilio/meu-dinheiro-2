package br.com.rotacilio.android.meudinheiro.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import br.com.rotacilio.android.meudinheiro.R
import br.com.rotacilio.android.meudinheiro.enum.CardFlag
import br.com.rotacilio.android.meudinheiro.model.Card

@BindingAdapter("cardDueDay")
fun TextView.setCardDueDay(card: Card) {
    val res = context.resources
    val txtDueDay = String.format(res.getString(R.string.template_string_card_due_day), card.dueDay)
    text = txtDueDay
}

@BindingAdapter("cardExpirationDate")
fun TextView.setCardExpirationDate(card: Card) {
    val res = context.resources
    val txtExpirationDate = String.format(res.getString(R.string.template_string_card_expiration_date), card.expirationDate)
    text = txtExpirationDate
}

@BindingAdapter("cardImageFlag")
fun ImageView.setCardImageFlag(card: Card) {
    val imgResId = when(card.flag.id.toInt()) {
        CardFlag.MASTERCARD.id -> R.drawable.ic_master_card
        CardFlag.VISA.id -> R.drawable.ic_visa_credit_card
        else -> R.drawable.ic_no_image
    }
    setImageResource(imgResId)
}
