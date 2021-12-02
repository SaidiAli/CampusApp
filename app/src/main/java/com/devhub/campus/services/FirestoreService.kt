package com.devhub.campus.services

import com.devhub.campus.app.models.UserModel
import com.devhub.campus.utils.FirestoreInterface
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import javax.inject.Inject

class FirestoreService
@Inject
constructor (
    private val db: FirebaseFirestore
) {
    fun write(user: UserModel, doc: String): Task<Void> {
        return db.collection("users").document(doc).set(user, SetOptions.merge())
    }

    fun readUserCollection(document: String): Task<DocumentSnapshot> {
        return db.collection("users").document(document).get()
    }
}