package br.com.rotacilio.android.meudinheiro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rotacilio.android.meudinheiro.databinding.CardListItemBinding
import br.com.rotacilio.android.meudinheiro.model.Card

class CardsListAdapter : RecyclerView.Adapter<CardsListAdapter.ViewHolder>() {

    var data: List<Card> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardListItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = data[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(
        private val binding: CardListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.card = card
        }
    }
}