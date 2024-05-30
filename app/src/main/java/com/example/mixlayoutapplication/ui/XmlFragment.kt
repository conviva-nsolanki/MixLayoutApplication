package com.example.mixlayoutapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mixlayoutapplication.MainViewModel
import com.example.mixlayoutapplication.R
import com.example.mixlayoutapplication.databinding.FragmentXmlBinding
import com.example.mixlayoutapplication.ui.theme.XmlItemAdapter
import kotlinx.coroutines.launch

class XmlFragment: Fragment(R.layout.fragment_xml) {

    private var _binding: FragmentXmlBinding? = null
    private val binding: FragmentXmlBinding get() = _binding!!

    private val adapter = XmlItemAdapter()

    private val mainViewModel: MainViewModel by activityViewModels()

    companion object {
        val TAG: String = "XmlFragment"
        fun newInstance(): XmlFragment {
            return XmlFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentXmlBinding.bind(view)

        binding.btnXml.setOnClickListener {
            Toast.makeText(requireContext(), "Button clicked", Toast.LENGTH_SHORT).show()
        }

        binding.ivXml.setOnClickListener {
            Toast.makeText(requireContext(), "ImageView clicked", Toast.LENGTH_SHORT).show()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect { uiState ->
                    binding.rvXml.layoutManager = LinearLayoutManager(requireContext())
                    adapter.submitList(uiState.nationalParks)
                    binding.rvXml.adapter = adapter
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}