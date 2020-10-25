package com.hackheroes.healthybody.ui.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackheroes.healthybody.db.Run
import com.hackheroes.healthybody.models.User
import com.hackheroes.healthybody.repository.main.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val getUserData: LiveData<User>
        get() = mainRepository.getUser

    fun fetchUserData() {
        mainRepository.fetchUserData()
    }

    fun logout() {
        mainRepository.logout()
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }

    fun getLatestRunByDate(): LiveData<Run> = mainRepository.getLatestRunByDate()

    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()

    fun isSignedIn(): LiveData<Boolean> {
        return mainRepository.isSignedIn
    }
}