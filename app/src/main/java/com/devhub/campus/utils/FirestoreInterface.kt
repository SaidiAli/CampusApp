package com.devhub.campus.utils

import com.devhub.campus.app.models.UserModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference

interface FirestoreInterface {
    suspend fun write(user: UserModel): Task<DocumentReference>
    suspend fun read(): Task<DocumentReference>
}