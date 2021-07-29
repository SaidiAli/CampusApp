package com.devhub.campus.di

import com.devhub.campus.services.AuthManager
import com.devhub.campus.services.FirebaseAuthService
import com.devhub.campus.services.FirebaseToLocalUserMapper
import com.devhub.campus.services.FirestoreToLocalUserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(
    includes = [FirebaseModule::class]
)
object AppModule {

    @Provides
    fun providesAuthModule(auth: FirebaseAuthService): AuthManager {
        return AuthManager(firebaseAuth = auth)
    }

    @Provides
    fun providesFirebaseToLocalUserMapper(): FirebaseToLocalUserMapper {
        return FirebaseToLocalUserMapper()
    }

    @Provides
    fun providesFirestoreToLocalUserMapper() : FirestoreToLocalUserMapper {
        return FirestoreToLocalUserMapper()
    }
}