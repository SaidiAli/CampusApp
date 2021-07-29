package com.devhub.campus.ui.auth


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhub.campus.app.models.UserModel
import com.devhub.campus.services.AuthManager
import com.devhub.campus.services.FirebaseAuthService
import com.devhub.campus.services.FirestoreService
import com.devhub.campus.services.FirestoreToLocalUserMapper
import com.devhub.campus.utils.Constants
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@HiltViewModel
class MainAuthViewModel
@Inject
constructor(
    private val firebaseService: FirebaseAuthService,
    private val firestore: FirestoreService,
    private val authManager: AuthManager,
    private val db: FirebaseFirestore,
    private val f: FirestoreToLocalUserMapper
) : ViewModel() {

    init {

        // initialise localUser
        authManager.localUser = UserModel()
    }

    private val u: UserModel = authManager.localUser

    /* Todo: add the year of study */

    var profileCreated: Boolean by mutableStateOf(false)

    var name: String by mutableStateOf(u.name.toString())
        private set

    var userName: String by mutableStateOf("")
        private set

    var number: String by mutableStateOf(u.phoneNumber.toString())
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

    var userExists: Boolean = false

    // cached user
    var user: FirebaseUser? = firebaseService.user

    fun getName(text: String) {
        name = text
    }

    fun getUsername(text: String) {
        userName = text
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

    fun checkIfUserExists(document: String?) {
        if (document != null) {
            db.collection("users").document(document).get().addOnSuccessListener { doc ->
                if(doc.exists()) {
                    userExists = true

                    viewModelScope.launch {
                        doc.data?.let { updateLocalUser(it) }
                    }
                }
            }
        }
    }

    fun updateLocalUser(
        userMap: Map<String, Any>? = null,
        google: GoogleSignInAccount? = null,
    ) {
        if (userMap != null) {
            authManager.localUser = f.map(userMap)
        }

        if(google != null) {
            authManager.localUser = UserModel(name = google.displayName, email = google.email,)
        }

        Log.d(Constants.DEBUG_TAG, "LocalUser updated: $u")
    }

    fun getRegistrationData(
        navigate: () -> Unit
    ) {
        /* TODO: inspect auth exceptions/errors - some are not captured in toast */
        if (password.isEmpty() or name.isEmpty()) {
            errorMessage = "Fill Out the form please"
            showErrorToast = true

            return
        }

        viewModelScope.launch {
            val task = firebaseService.signUp("", password)

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
        if (password.isEmpty()) {
            errorMessage = "Fill Out the form please"
            showErrorToast = true

            return
        }

        viewModelScope.launch {
            val task = firebaseService.signIn("", password)

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

    fun createProfile() {
        /*Todo: Consider checking for individual properties*/

        if (userName.isEmpty() or campusName.isEmpty() or programOfStudy.isEmpty()) {
            errorMessage = "Fill Out the form please"
            showErrorToast = true

            return
        }

        val user = UserModel(
            id = u.id,
            name = name,
            email = u.email,
            pos = programOfStudy,
            userName = userName,
            phoneNumber = number,
        )

        viewModelScope.launch {
            val task = firestore.write(user)

            if(!task.isComplete) {
                loadingState = true
            }

            task.addOnCompleteListener {
                loadingState = false

                if(it.isSuccessful) {
                    profileCreated = true

                    // Todo: Redirect to feed screen
                } else if(it.isCanceled) {
                    showErrorToast = true
                    errorMessage = it.exception?.message
                }
            }
        }
    }
}
