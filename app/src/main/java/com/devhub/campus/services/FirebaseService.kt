package com.devhub.campus.services

import android.util.Log
import com.devhub.campus.utils.FirebaseInterface
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseService
    @Inject
    constructor(
        private val auth: FirebaseAuth
    ): FirebaseInterface {

    override fun signUp(email: String, password: String): Task<AuthResult> {
        return auth.createUserWithEmailAndPassword(email, password)
    }

    override fun signIn(): Task<AuthResult> {
        TODO("Not yet implemented")
    }
}