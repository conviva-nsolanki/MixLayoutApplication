package com.example.mixlayoutapplication

import android.app.Activity
import android.os.Bundle
import android.util.Xml
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.conviva.apptracker.controller.TrackerController
import com.example.mixlayoutapplication.databinding.ActivityMainBinding
import com.example.mixlayoutapplication.ui.ComposeFragment
import com.example.mixlayoutapplication.ui.XmlFragment
import com.example.mixlayoutapplication.ui.theme.MixLayoutApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var tracker: TrackerController

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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MixLayoutApplicationTheme {
        Greeting("Android")
    }
}