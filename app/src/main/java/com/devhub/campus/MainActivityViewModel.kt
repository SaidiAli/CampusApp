package com.devhub.campus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devhub.campus.services.AuthManager
import com.devhub.campus.services.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    authManager: AuthManager
): ViewModel() {
    var redirectToAuthActivity = mutableStateOf(false)

    init {
        // check that the user is authenticated and update local user info
        viewModelScope.launch {
            if(authManager.authState == AuthState.LOGGED_OUT) {
                true.also { redirectToAuthActivity.value = it };
            }
        }
    }
}