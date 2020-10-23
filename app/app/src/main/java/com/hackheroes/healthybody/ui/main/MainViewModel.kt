package com.hackheroes.healthybody.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hackheroes.healthybody.models.User
import com.hackheroes.healthybody.repository.main.MainRepository

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val getUserData: LiveData<User>
        get() = mainRepository.getUser

    fun fetchUserData() {
        mainRepository.fetchUserData()
    }

}