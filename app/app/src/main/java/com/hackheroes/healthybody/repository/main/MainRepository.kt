package com.hackheroes.healthybody.repository.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hackheroes.healthybody.db.Run
import com.hackheroes.healthybody.db.RunDAO
import com.hackheroes.healthybody.models.User
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    val appContext: Context,
    val firebaseAuth: FirebaseAuth,
    var fireStore: FirebaseFirestore,
    val runDao: RunDAO
) {

    private val userId: String = firebaseAuth.currentUser?.uid!!

    private val personCollectionRef = Firebase.firestore.collection("users")

    private val documentReference: DocumentReference = personCollectionRef.document(userId)

    private lateinit var user: User

    protected val _isSignedIn: MutableLiveData<Boolean> = MutableLiveData()

    val isSignedIn: LiveData<Boolean>
        get() = _isSignedIn

    private val _user: MutableLiveData<User> = MutableLiveData()

    val getUser: LiveData<User>
        get() = _user

    fun fetchUserData() {
        documentReference.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                return@addSnapshotListener
            }

            querySnapshot?.let {
                user = User(
                    name = it.getString("name"),
                    email = it.getString("email"),
                    age = it.getString("age"),
                    bmi = it.getString("bmi"),
                    height = it.getString("height"),
                    sex = it.getString("sex"),
                    weight = it.getString("weight"),
                    bmr = it.getString("bmr")
                )
                _user.value = user
            }

        }
    }

    fun logout() {
        firebaseAuth.signOut()
        setIsAuthenticated(isAuthenticated = false)
    }

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    fun getLatestRunByDate(): LiveData<Run> = runDao.getLatestRunByDate()

    private fun setIsAuthenticated(isAuthenticated: Boolean) {
        _isSignedIn.value = isAuthenticated
    }
}
