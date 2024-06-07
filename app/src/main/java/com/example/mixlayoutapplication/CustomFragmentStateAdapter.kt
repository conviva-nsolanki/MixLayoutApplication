package com.example.mixlayoutapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mixlayoutapplication.ui.ComposeFragment
import com.example.mixlayoutapplication.ui.XmlFragment

class CustomFragmentStateAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> XmlFragment.newInstance()
            else -> ComposeFragment.newInstance()
        }
    }
}