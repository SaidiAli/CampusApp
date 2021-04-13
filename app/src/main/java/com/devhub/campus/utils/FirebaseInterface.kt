package com.devhub.campus.utils

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface FirebaseInterface {

    suspend fun signUp(email: String, password: String): Task<AuthResult>

    suspend fun signIn(): Task<AuthResult>

}