package com.devhub.campus.ui.profile

import androidx.lifecycle.ViewModel
import com.devhub.campus.services.FirebaseAuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    auth: FirebaseAuthService
) : ViewModel() {
    val user = auth.user
}