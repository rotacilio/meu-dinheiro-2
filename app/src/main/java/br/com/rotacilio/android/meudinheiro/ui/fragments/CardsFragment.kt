package br.com.rotacilio.android.meudinheiro.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.rotacilio.android.meudinheiro.R
import br.com.rotacilio.android.meudinheiro.adapter.CardsListAdapter
import br.com.rotacilio.android.meudinheiro.databinding.FragmentCardsBinding
import br.com.rotacilio.android.meudinheiro.extension.dp
import br.com.rotacilio.android.meudinheiro.ui.components.MarginItemDecoration
import br.com.rotacilio.android.meudinheiro.viewmodel.CardsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsFragment : Fragment() {

    private lateinit var binding: FragmentCardsBinding
    private val viewModel: CardsFragmentViewModel by viewModel()
    private lateinit var cardAdapter: CardsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        cardAdapter = CardsListAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        configureObservers()
    }

    private fun setupUi() {
        with(binding) {
            rvCardsList.addItemDecoration(MarginItemDecoration(12.dp))
            rvCardsList.adapter = cardAdapter
        }
    }

    private fun configureObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CardsFragmentViewModel.ViewState.Loading -> {
                    Log.d(tag, "configureObservers: carregando...")
                }
                is CardsFragmentViewModel.ViewState.CardsLoaded -> {
                    cardAdapter.data = state.cards
                }
                is CardsFragmentViewModel.ViewState.Error -> {
                    Log.e(tag, "configureObservers: Error: ${state.errorMessage}")
                }
            }
        }
    }
}