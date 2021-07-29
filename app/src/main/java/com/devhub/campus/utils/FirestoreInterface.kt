package com.devhub.campus.utils

import com.devhub.campus.app.models.UserModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot

interface FirestoreInterface {
    suspend fun write(user: UserModel): Task<DocumentReference>
    suspend fun readUserCollection(document: String): Task<DocumentSnapshot>
}