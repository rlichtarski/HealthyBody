package com.hackheroes.healthybody.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hackheroes.healthybody.repository.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AuthRepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(
        @ApplicationContext appContext: Context,
        firebaseAuth: FirebaseAuth,
        fireStore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepository(appContext, firebaseAuth, fireStore)
    }

}