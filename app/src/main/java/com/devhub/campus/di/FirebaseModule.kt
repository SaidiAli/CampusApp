package com.devhub.campus.di

import com.devhub.campus.services.FirebaseAuthService
import com.devhub.campus.services.FirestoreService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
object FirebaseModule {

    @Provides
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return Firebase.auth
    }

    @Provides
    fun provideFirestoreInstance(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    fun provideFirebaseService(auth: FirebaseAuth): FirebaseAuthService {
        return FirebaseAuthService(auth)
    }

    @Provides
    fun provideFirestoreService(db: FirebaseFirestore): FirestoreService {
        return FirestoreService(db)
    }
}