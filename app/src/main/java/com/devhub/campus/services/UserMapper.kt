package com.devhub.campus.services

import com.devhub.campus.app.models.UserModel
import com.devhub.campus.utils.ModelMapper
import com.google.firebase.auth.FirebaseUser

class UserMapper : ModelMapper<FirebaseUser, UserModel> {
    override fun mapEntityToModel(entity: FirebaseUser): UserModel {
        return UserModel(
            name = entity.displayName,
            phoneNumber = entity.phoneNumber,
            email = entity.email,
        )
    }

    override fun mapModelToEntity(model: UserModel): FirebaseUser {
        TODO("Not yet implemented")
    }
}