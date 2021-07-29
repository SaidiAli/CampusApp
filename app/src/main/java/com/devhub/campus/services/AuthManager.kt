package com.devhub.campus.services

import android.app.Activity
import android.provider.Settings.Global.getString
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.devhub.campus.R
import com.devhub.campus.app.models.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import javax.inject.Inject

enum class AuthState {
    LOGGED_OUT,
    LOGGED_IN
}

class AuthManager @Inject constructor(
    firebaseAuth: FirebaseAuthService,
) {
    val authState: AuthState =
        if (firebaseAuth.user != null) AuthState.LOGGED_IN else AuthState.LOGGED_OUT

    lateinit var localUser: UserModel

    init {

    }
}
