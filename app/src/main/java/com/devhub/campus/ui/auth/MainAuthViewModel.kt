package com.devhub.campus.ui.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhub.campus.app.models.UserModel
import com.devhub.campus.services.AuthManager
import com.devhub.campus.services.FirebaseAuthService
import com.devhub.campus.services.FirestoreService
import com.devhub.campus.services.FirestoreToLocalUserMapper
import com.devhub.campus.utils.Constants
import com.devhub.campus.utils.Event
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
    lateinit var _id: String

    /* Todo: add the year of study */

    private var _profileCreated = MutableLiveData<Event<Boolean>>()
    var profileCreated: LiveData<Event<Boolean>> = _profileCreated

    var email: String = ""

    var name: String by mutableStateOf("")
        private set

    var userName: String by mutableStateOf("")
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

    private var _userExists = MutableLiveData<Event<Boolean>>()
    var userExists: LiveData<Event<Boolean>> = _userExists

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
                _userExists.value = Event(doc.exists())

                if(doc.exists()) {
                    viewModelScope.launch {
                        doc.data?.let { updateLocalUser(it) }
                    }
                }
            }
        } else {
            _id = document ?: ""
            _userExists.value = Event(false)
        }
    }

    fun updateLocalUser(
        userMap: Map<String, Any>? = null,
        nomre: String? = null,
        e: String? = null
    ) {
        if (userMap != null) {
            authManager.localUser = f.map(userMap)
        }

        name = nomre ?: ""
        email = e ?: ""
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
            id = _id,
            name = name,
            email = email,
            pos = programOfStudy,
            userName = userName,
            phoneNumber = number,
        )

        viewModelScope.launch {
            val task = firestore.write(user, user.id.toString()) // check for nullability

            if(!task.isComplete) {
                loadingState = true
            }

            task.addOnCompleteListener {
                loadingState = false

                if(it.isSuccessful) {
                    _profileCreated.value = Event(true)

                    Log.d(Constants.DEBUG_TAG, "User updated: ${authManager.localUser}")

                    // Todo: Redirect to feed screen
                } else if(it.isCanceled) {
                    showErrorToast = true
                    errorMessage = it.exception?.message
                }
            }

            task.addOnFailureListener {
                showErrorToast = true
                errorMessage = it.message

                Log.d(
                    Constants.DEBUG_TAG,
                    "failed to create userProfile: docId ${user.id}, exception: ${it.message}"
                )
            }
        }
    }
}
