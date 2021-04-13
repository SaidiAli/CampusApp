package com.devhub.campus.di

import com.devhub.campus.services.FirebaseService
import com.devhub.campus.utils.FirebaseInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
object FirebaseModule {

    lateinit var auth: FirebaseAuth

    @Provides
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        auth = Firebase.auth

        return auth
    }
}