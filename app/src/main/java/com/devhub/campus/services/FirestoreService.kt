package com.devhub.campus.services

import com.devhub.campus.app.models.UserModel
import com.devhub.campus.utils.FirestoreInterface
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirestoreService
@Inject
constructor (
    private val db: FirebaseFirestore
) : FirestoreInterface {
    override suspend fun write(user: UserModel): Task<DocumentReference> {
        return db.collection("users").add(user)
    }

    override suspend fun readUserCollection(document: String): Task<DocumentSnapshot> {
        return db.collection("users").document(document).get()
    }
}