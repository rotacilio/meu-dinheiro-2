package br.com.rotacilio.android.meudinheiro.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.rotacilio.android.meudinheiro.databinding.FragmentCardsBinding
import br.com.rotacilio.android.meudinheiro.extensions.dp
import br.com.rotacilio.android.meudinheiro.ui.adapter.CardsListAdapter
import br.com.rotacilio.android.meudinheiro.ui.components.MarginItemDecoration
import br.com.rotacilio.android.meudinheiro.viewmodel.CardsFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsFragment : Fragment() {

    private lateinit var binding: FragmentCardsBinding
    private val viewModel: CardsFragmentViewModel by viewModel()
    private val cardAdapter: CardsListAdapter = CardsListAdapter {
        Log.d(tag, "onCardClick: ${it.nickname}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCardsBinding.inflate(inflater, container, false).apply {
        binding = this
        viewModel = viewModel
        lifecycleOwner = viewLifecycleOwner
    }.root

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
        viewModel.apply {
            viewState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is CardsFragmentViewModel.ViewState.Loading -> {
                        binding.layoutLoadingView.visibility = View.VISIBLE
                        binding.rvCardsList.visibility = View.GONE
                    }
                    is CardsFragmentViewModel.ViewState.Error -> {
                        Log.e(tag, "configureObservers: error: ${state.errorMessage}")
                    }
                    is CardsFragmentViewModel.ViewState.CardsLoaded -> {
                        binding.layoutLoadingView.visibility = View.GONE
                        binding.rvCardsList.visibility = View.VISIBLE
                        cardAdapter.submitList(state.cards)
                    }
                }
            }
        }
    }
}