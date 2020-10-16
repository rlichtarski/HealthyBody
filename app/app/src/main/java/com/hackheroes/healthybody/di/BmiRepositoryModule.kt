package com.hackheroes.healthybody.di

import com.hackheroes.healthybody.api.BmiApi
import com.hackheroes.healthybody.repository.BmiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object BmiRepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        bmiApi: BmiApi
    ): BmiRepository{
        return BmiRepository(bmiApi)
    }

}