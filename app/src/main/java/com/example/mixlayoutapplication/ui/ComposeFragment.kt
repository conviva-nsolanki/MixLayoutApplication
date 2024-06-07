package com.example.mixlayoutapplication.ui

import android.os.Bundle
import android.view.View
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mixlayoutapplication.MainViewModel
import com.example.mixlayoutapplication.R
import com.example.mixlayoutapplication.databinding.FragmentComposeBinding

class ComposeFragment: Fragment(R.layout.fragment_compose) {

    private var _binding: FragmentComposeBinding? = null
    private val binding: FragmentComposeBinding
        get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

    companion object {
        val TAG: String = "ComposeFragment"
        fun newInstance(): ComposeFragment {
            return ComposeFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentComposeBinding.bind(view)
        mainViewModel.loadNationalPark("AK")
        binding.composeView.setContent {
            ComposeContent(modifier = Modifier, viewModel = mainViewModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}