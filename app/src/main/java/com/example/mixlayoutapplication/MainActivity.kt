package com.example.mixlayoutapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.conviva.apptracker.ConvivaAppAnalytics
import com.conviva.apptracker.configuration.TrackerConfiguration
import com.conviva.apptracker.tracker.LogLevel
import com.example.mixlayoutapplication.databinding.ActivityMainBinding
import com.example.mixlayoutapplication.util.APP_NAME
import com.example.mixlayoutapplication.util.CUATOMER_KEY
import com.example.mixlayoutapplication.util.USER_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeConvivaTracker(this)
        binding.bottomNavigationMenu.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_xml -> {
                    binding.viewPager.setCurrentItem(0, false)
                }
                R.id.navigation_compose -> {
                    binding.viewPager.setCurrentItem(1, false)
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                when(position) {
                    0 -> {
                        binding.bottomNavigationMenu.menu.findItem(R.id.navigation_xml).setChecked(true)
                    }
                    else -> {
                        binding.bottomNavigationMenu.menu.findItem(R.id.navigation_compose).setChecked(true)
                    }
                }
            }

        })
        setupViewPager(binding.viewPager)
    }

    private fun setupViewPager(viewPager: ViewPager2) {
        val viewPagerAdapter = CustomFragmentStateAdapter(this)
        viewPager.adapter = viewPagerAdapter
    }

    private fun initializeConvivaTracker(context: Context) {
        val configuration = TrackerConfiguration(APP_NAME)
        configuration.setLogLevel(LogLevel.DEBUG)

        ConvivaAppAnalytics.createTracker(
            context,
            CUATOMER_KEY,
            APP_NAME,
            configuration
        )?.also { tracker ->
            ConvivaAppAnalytics.setTrackerAsDefault(tracker)
        }

        ConvivaAppAnalytics.getTracker()?.subject?.userId = USER_ID
    }
}