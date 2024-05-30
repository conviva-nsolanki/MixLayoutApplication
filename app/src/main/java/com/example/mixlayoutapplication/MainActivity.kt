package com.example.mixlayoutapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.conviva.apptracker.controller.TrackerController
import com.example.mixlayoutapplication.databinding.ActivityMainBinding
import com.example.mixlayoutapplication.ui.ComposeFragment
import com.example.mixlayoutapplication.ui.XmlFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var tracker: TrackerController

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationMenu.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_xml -> {
                    val fm = this@MainActivity.supportFragmentManager
                    val fragment = fm.findFragmentByTag(XmlFragment.TAG)?: XmlFragment.newInstance()
                    fm.beginTransaction().replace(R.id.fl_container, fragment, XmlFragment.TAG)
                        .addToBackStack(null)
                        .commit()
                }
                R.id.navigation_compose -> {
                    val fm = this@MainActivity.supportFragmentManager
                    val fragment = fm.findFragmentByTag(ComposeFragment.TAG)?: ComposeFragment.newInstance()
                    fm.beginTransaction().replace(R.id.fl_container, fragment, ComposeFragment.TAG)
                        .addToBackStack(null)
                        .commit()
                }
            }
            return@setOnItemSelectedListener true
        }
    }
}