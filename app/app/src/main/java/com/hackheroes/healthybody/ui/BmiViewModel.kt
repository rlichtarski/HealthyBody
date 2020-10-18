package com.hackheroes.healthybody.ui

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hackheroes.healthybody.repository.BmiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class BmiViewModel @ViewModelInject constructor(
    private val bmiRepository: BmiRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    fun getBmi(): LiveData<DataState<BmiViewState>> {
        return bmiRepository.bmi()
    }

}