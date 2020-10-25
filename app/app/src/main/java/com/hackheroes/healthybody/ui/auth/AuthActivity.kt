package com.hackheroes.healthybody.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hackheroes.healthybody.MainActivity
import com.hackheroes.healthybody.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthActivity: AppCompatActivity() {

    @Inject
    lateinit var mAuth: FirebaseAuth

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //mAuth.signOut()

        findNavController(R.id.auth_nav_host_fragment)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        authViewModel.isSignedIn().observe(this, Observer {
            if(it) navMainActivity()
        })
    }

    private fun navMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        checkLoggedInState()
    }

    private fun checkLoggedInState() {
        if (mAuth.currentUser == null) { // not logged in
        } else {
            navMainActivity()
        }
    }
}