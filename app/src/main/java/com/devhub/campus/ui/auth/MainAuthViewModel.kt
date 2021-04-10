package com.devhub.campus.ui.auth

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainAuthViewModel
@Inject
constructor(
    private val auth: FirebaseAuth
) : ViewModel() {
    @Inject lateinit var boom: String

    private val TAG: String = "APP_DEBUG"

    init {
        Log.d(TAG, boom)
    }

    var name: String by mutableStateOf("")
    private set

    var email: String by mutableStateOf("")
        private set

    var number: String by mutableStateOf("")
        private set

    var password: String by mutableStateOf("")
        private set

    var campusName: String by mutableStateOf("")
        private set

    var programOfStudy: String by mutableStateOf("")
        private set

    fun getName(text: String) {
        name = text
    }

    fun getEmail(text: String) {
        email = text
    }

    fun getNumber(text: String) {
        number = text
    }

    fun getCampusName(text: String) {
        campusName = text
    }

    fun getPOS(text: String) {
        programOfStudy = text
    }

    fun getPassword(text: String) {
        password = text
    }

    fun getRegistrationData(
        navigate: () -> Unit
    ) {
        /* TODO: use implementation from a firebase service class */

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful) {
                    Log.i("APP_INFO", "Signup successfull: ${it.result?.user.toString()}")
                } else {
                    Log.i("APP_INFO", "Signup failed: ${it.exception}")
                }
            }
    }

    fun getLoginData(
        navigate: () -> Unit
    ) {
        navigate()
        /* TODO("handle login logic") */
    }

    fun handleOtpCodeVerification(
        navigate: () -> Unit
    ) {
        navigate()
        /* TODO("handle otp code verification logic") */
    }
}
