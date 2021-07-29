package com.devhub.campus.services

import com.devhub.campus.utils.auth.FirebaseAuthInterface
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseAuthService
    @Inject
    constructor(
        private val auth: FirebaseAuth
    ): FirebaseAuthInterface {

    var user: FirebaseUser? = auth.currentUser

    override suspend fun signUp(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override suspend fun signIn(email: String, password: String): Task<AuthResult> {
        return auth.signInWithEmailAndPassword(email, password)
    }
}