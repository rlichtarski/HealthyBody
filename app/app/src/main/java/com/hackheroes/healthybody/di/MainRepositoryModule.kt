package com.hackheroes.healthybody.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hackheroes.healthybody.repository.auth.AuthRepository
import com.hackheroes.healthybody.repository.main.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object MainRepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        @ApplicationContext appContext: Context,
        firebaseAuth: FirebaseAuth,
        fireStore: FirebaseFirestore
    ): MainRepository {
        return MainRepository(appContext, firebaseAuth, fireStore)
    }

}