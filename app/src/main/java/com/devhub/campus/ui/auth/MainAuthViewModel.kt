package com.devhub.campus.ui.auth


import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhub.campus.app.models.UserModel
import com.devhub.campus.services.FirebaseAuthService
import com.devhub.campus.services.FirestoreService
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainAuthViewModel
@Inject
constructor(
    private val firebaseService: FirebaseAuthService,
    private val firestore: FirestoreService
) : ViewModel() {

    /* Todo: add the year of study */

    var name: String by mutableStateOf("")
    private set

    var userName: String by mutableStateOf("")
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

    // cached user
    var user: FirebaseUser? = firebaseService.user

    fun getName(text: String) {
        name = text
    }

    fun getUsername(text: String) {
        userName = text
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
        if (email.isEmpty() or password.isEmpty() or name.isEmpty()) {
            errorMessage = "Fill Out the form please"
            showErrorToast = true

            return
        }

        viewModelScope.launch {
            val task = firebaseService.signUp(email, password)

            if(!task.isComplete) {
                loadingState = true
            }

            task.addOnCompleteListener {
                loadingState = false

                if(it.isSuccessful) {
                    navigate()
                } else {
                    errorMessage = it.exception?.message
                    showErrorToast = true
                }
            }
        }
    }

    fun getLoginData(
        navigate: () -> Unit
    ) {
        if (email.isEmpty() or password.isEmpty()) {
            errorMessage = "Fill Out the form please"
            showErrorToast = true

            return
        }

        viewModelScope.launch {
            val task = firebaseService.signIn(email, password)

            if(!task.isComplete) {
                loadingState = true
            }

            task.addOnCompleteListener {
                loadingState = false

                if(it.isSuccessful) {
                    navigate()
                } else {
                    errorMessage = it.exception?.message
                    showErrorToast = true
                }
            }
        }
    }

    fun handleOtpCodeVerification(
        navigate: () -> Unit
    ) {
        navigate()
        /* TODO("handle otp code verification logic") */
    }

    fun createProfile(
        navigate: () -> Unit
    ) {
        /*Todo: Consider checking for individual properties*/

        if (userName.isEmpty() or campusName.isEmpty() or programOfStudy.isEmpty()) {
            errorMessage = "Fill Out the form please"
            showErrorToast = true

            return
        }

        val user = UserModel(name = name, email = email, pos = programOfStudy, userName = userName)

        viewModelScope.launch {
            val task = firestore.write(user)

            if(!task.isComplete) {
                loadingState = true
            }

            task.addOnCompleteListener {
                loadingState = false

                if(it.isSuccessful) {
                    navigate()
                } else if(it.isCanceled) {
                    showErrorToast = true
                    errorMessage = it.exception?.message
                }
            }
        }
    }
}
