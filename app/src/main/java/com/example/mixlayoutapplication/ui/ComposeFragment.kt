package com.example.mixlayoutapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mixlayoutapplication.MainViewModel

class ComposeFragment: Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    companion object {
        val TAG: String = "ComposeFragment"
        fun newInstance(): ComposeFragment {
            return ComposeFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeContent(modifier = Modifier, viewModel = mainViewModel)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.loadNationalPark("AK")
    }

}