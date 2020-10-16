package com.hackheroes.healthybody.repository

import com.hackheroes.healthybody.api.BmiApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BmiRepository @Inject constructor(
    private val bmiApi: BmiApi
) {

}