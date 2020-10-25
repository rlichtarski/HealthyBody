package com.hackheroes.healthybody.ui.auth

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hackheroes.healthybody.repository.auth.AuthRepository

class AuthViewModel @ViewModelInject constructor(
    private val authRepository: AuthRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    fun register(username: String, email: String, password: String) {
        authRepository.attemptRegistration(username, email, password)
    }

    fun login(email: String, password: String) {
        authRepository.attemptLogin(email, password)
    }

    fun isSignedIn(): LiveData<Boolean> {
        return authRepository.isSignedIn
    }

}