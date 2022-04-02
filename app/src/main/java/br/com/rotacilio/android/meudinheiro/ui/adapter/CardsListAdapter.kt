package br.com.rotacilio.android.meudinheiro.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rotacilio.android.meudinheiro.databinding.CardListItemBinding
import br.com.rotacilio.android.meudinheiro.model.Card

class CardsListAdapter : ListAdapter<Card, CardsListAdapter.ViewHolder>(CardsListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.build(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)
    }

    class ViewHolder(
        private val binding: CardListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.card = card
        }

        companion object {
            fun build(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = CardListItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CardsListDiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean = oldItem == newItem

}
