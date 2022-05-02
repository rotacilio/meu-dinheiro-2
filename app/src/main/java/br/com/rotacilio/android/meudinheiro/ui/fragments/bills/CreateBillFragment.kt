package br.com.rotacilio.android.meudinheiro.ui.fragments.bills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.rotacilio.android.meudinheiro.databinding.FragmentCreateBillBinding

class CreateBillFragment : Fragment() {

    private lateinit var binding: FragmentCreateBillBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCreateBillBinding.inflate(inflater, container, false).apply {
        binding = this
        lifecycleOwner = this@CreateBillFragment
    }.root
}