package br.com.rotacilio.android.meudinheiro.ui.fragments.cards

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.rotacilio.android.meudinheiro.databinding.FragmentCardsBinding
import br.com.rotacilio.android.meudinheiro.extensions.dp
import br.com.rotacilio.android.meudinheiro.ui.adapter.CardsListAdapter
import br.com.rotacilio.android.meudinheiro.ui.components.MarginItemDecoration
import br.com.rotacilio.android.meudinheiro.viewmodel.CardsFragmentViewModel
import br.com.rotacilio.android.meudinheiro.viewmodel.GenericViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CardsFragment : Fragment() {

    private lateinit var binding: FragmentCardsBinding
    private val viewModel: CardsFragmentViewModel by viewModel()
    private val cardAdapter: CardsListAdapter = CardsListAdapter {
        findNavController().navigate(
            CardsFragmentDirections.actionCardsFragmentToCardDetailFragment(it))
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

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    private fun setupUi() {
        with(binding) {
            rvCardsList.addItemDecoration(MarginItemDecoration(12.dp))
            rvCardsList.adapter = cardAdapter
        }
    }

    private fun configureObservers() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is GenericViewModel.UiState.Loading -> {
                        binding.layoutLoadingView.visibility =
                            if (state.show) View.VISIBLE else View.GONE
                        binding.rvCardsList.visibility =
                            if (!state.show) View.VISIBLE else View.GONE
                    }
                    is GenericViewModel.UiState.Error -> {
                        Log.e(tag, "configureObservers: error: ${state.errorMessage}")
                    }
                    is CardsFragmentViewModel.UiStateVC.CardsLoaded -> {
                        cardAdapter.submitList(state.cards)
                    }
                    else -> Unit
                }
            }
        }
    }
}