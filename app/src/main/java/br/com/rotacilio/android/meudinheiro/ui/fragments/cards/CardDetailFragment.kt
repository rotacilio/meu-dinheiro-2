package br.com.rotacilio.android.meudinheiro.ui.fragments.cards

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.rotacilio.android.meudinheiro.databinding.FragmentCardDetailBinding

class CardDetailFragment : Fragment() {

    private lateinit var binding: FragmentCardDetailBinding
    private val args: CardDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCardDetailBinding.inflate(inflater, container, false).apply {
        binding = this
        lifecycleOwner = this@CardDetailFragment
        card = args.selectedCard
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}