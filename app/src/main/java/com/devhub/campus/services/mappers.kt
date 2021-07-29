package com.devhub.campus.services

import com.devhub.campus.app.models.Campus
import com.devhub.campus.app.models.College
import com.devhub.campus.app.models.UserModel
import com.devhub.campus.utils.Mapper
import com.google.firebase.auth.FirebaseUser

class FirebaseToLocalUserMapper : Mapper<FirebaseUser, UserModel> {
    override fun map(from: FirebaseUser): UserModel {
        return UserModel(
            id = from.uid,
            name = from.displayName,
            email = from.email,
            phoneNumber = from.phoneNumber
        )
    }
}

class FirestoreToLocalUserMapper: Mapper<Map<String, Any>, UserModel> {
    override fun map(from: Map<String, Any>): UserModel {
        return UserModel(
            id = from["id"] as String,
            name = from["name"] as String,
            userName = from["userName"] as String,
            phoneNumber = from["phoneNumber"] as String,
            email = from["email"] as String,
            bio = from["bio"] as String,
            college = from["college"] as College,
            campusName = from["campusName"] as Campus,
            // more to come
        )
    }
}
