package com.devhub.campus.utils

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthInterface {

    suspend fun signUp(email: String, password: String): Task<AuthResult>

    suspend fun signIn(email: String, password: String): Task<AuthResult>

}