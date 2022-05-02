package br.com.rotacilio.android.meudinheiro.ui.fragments.bills

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.rotacilio.android.meudinheiro.R
import br.com.rotacilio.android.meudinheiro.databinding.FragmentBillsBinding

class BillsFragment : Fragment() {

    private lateinit var binding: FragmentBillsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentBillsBinding.inflate(inflater, container, false).apply {
        binding = this
        lifecycleOwner = this@BillsFragment
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            fabAddExpense.setOnClickListener {
                findNavController()
                    .navigate(BillsFragmentDirections.actionBillsFragmentToCreateBillFragment())
            }
        }
    }
}