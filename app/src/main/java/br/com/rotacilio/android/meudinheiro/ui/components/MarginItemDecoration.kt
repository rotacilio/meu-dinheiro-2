package br.com.rotacilio.android.meudinheiro.ui.components

import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import br.com.rotacilio.android.meudinheiro.adapter.CardsListAdapter

class MarginItemDecoration(private val spaceSize: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        with (outRect) {
            val cardsListAdapter = parent.adapter as CardsListAdapter
            if (position == cardsListAdapter.itemCount - 1) {
                bottom = spaceSize
            }
        }
    }
}