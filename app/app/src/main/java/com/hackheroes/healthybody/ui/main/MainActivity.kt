package com.hackheroes.healthybody.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hackheroes.healthybody.R
import com.hackheroes.healthybody.util.Constants.Companion.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToDashboardFragmentIfNeeded(intent)

        findNavController(R.id.main_nav_host_fragment)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToDashboardFragmentIfNeeded(intent)
    }

    private fun navigateToDashboardFragmentIfNeeded(intent: Intent?) {
        if(intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            findNavController(R.id.main_nav_host_fragment).navigate(
                R.id.action_global_dashboardFragment
            )
        }
    }
}