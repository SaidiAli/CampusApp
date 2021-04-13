package com.devhub.campus.utils

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface FirebaseInterface {

    fun signUp(email: String, password: String): Task<AuthResult>

    fun signIn(): Task<AuthResult>

}