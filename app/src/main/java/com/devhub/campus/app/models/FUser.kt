package com.devhub.campus.app.models

data class FUser (
    val uid: String,
    val displayName: String? = null,
    val phoneNumber: String? = null,
    val provider: String? = null,
    val email: String? = null,
)