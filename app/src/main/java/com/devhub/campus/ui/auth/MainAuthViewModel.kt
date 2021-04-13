package com.devhub.campus.ui.auth


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.devhub.campus.services.FirebaseService
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainAuthViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var firebaseService: FirebaseService

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

    var showErrorToast: Boolean by mutableStateOf(false)
    var errorMessage: String? by mutableStateOf("")
    var loadingState: Boolean by mutableStateOf(false)

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
        /* TODO: inspect auth exceptions/errors - some are not captured in toast */
        if (email.isEmpty() or password.isEmpty()) {
            return
        }

        val task = firebaseService.signUp(email, password)

        if(!task.isComplete) {
            loadingState = true
        }

        task.addOnCompleteListener {
            loadingState = false

            if(it.isSuccessful) {
                navigate()
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
