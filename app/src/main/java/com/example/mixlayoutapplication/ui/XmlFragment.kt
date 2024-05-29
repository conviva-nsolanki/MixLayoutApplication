package com.example.mixlayoutapplication.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mixlayoutapplication.R
import com.example.mixlayoutapplication.databinding.FragmentComposeBinding
import com.example.mixlayoutapplication.databinding.FragmentXmlBinding
import com.example.mixlayoutapplication.ui.theme.XmlItemAdapter

class XmlFragment: Fragment(R.layout.fragment_xml) {

    private var _binding: FragmentXmlBinding? = null
    private val binding: FragmentXmlBinding get() = _binding!!

    private val adapter = XmlItemAdapter()

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

        }

        binding.ivXml.setOnClickListener {

        }

        binding.rvXml.layoutManager = LinearLayoutManager(requireContext())
        binding.rvXml.adapter = adapter
        adapter.submitList(listOf("a","b","c","d","e","f","g","h","i","j","k","l","m","l","o",))

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}