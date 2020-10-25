package com.hackheroes.healthybody.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hackheroes.healthybody.R
import com.hackheroes.healthybody.ui.auth.AuthActivity
import com.hackheroes.healthybody.util.Constants.Companion.ACTION_SHOW_TRACKING_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToDashboardFragmentIfNeeded(intent)

        findNavController(R.id.main_nav_host_fragment)

        subscribeObservers()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToDashboardFragmentIfNeeded(intent)
    }

    private fun subscribeObservers() {
        mainViewModel.isSignedIn().observe(this, Observer { isAuthenticated ->
            if (!isAuthenticated) navAuthActivity()
        })
    }

    private fun navigateToDashboardFragmentIfNeeded(intent: Intent?) {
        if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            findNavController(R.id.main_nav_host_fragment).navigate(
                R.id.action_global_dashboardFragment
            )
        }
    }

    private fun navAuthActivity() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}