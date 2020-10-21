package com.hackheroes.healthybody

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hackheroes.healthybody.ui.BmiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    lateinit var bmiViewModel: BmiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bmiViewModel = ViewModelProvider(this).get(BmiViewModel::class.java)

        findNavController(R.id.main_nav_host_fragment)

        subscribeObservers()
    }

    private fun subscribeObservers() {
        bmiViewModel.getBmi().observe(this, Observer { dataState ->
            dataState.data?.let {data ->
                data.data?.let { event ->
                    event.getContentIfNotHandled()?.let {
                        it.bmiResponse?.let { response ->
                            Log.d(TAG, "subscribeObservers: response: $response")
                        }
                    }
                }

            }
        })
    }
}