package com.hackheroes.healthybody.repository.auth

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class AuthRepository
@Inject
constructor(
    val appContext: Context,
    val firebaseAuth: FirebaseAuth,
    var fireStore: FirebaseFirestore
) {

    private val TAG: String = "AppDebug"

    private var repositoryJob: Job? = null

    private lateinit var userId: String

    private val personCollectionRef = Firebase.firestore.collection("users")

    protected val _isSignedIn: MutableLiveData<Boolean> = MutableLiveData()

    val isSignedIn: LiveData<Boolean>
        get() = _isSignedIn

    fun attemptRegistration(username: String, email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            Timber.e("attemptRegistration: successfully registered a new user")
                            userId = firebaseAuth.currentUser?.uid!!
                            val documentReference: DocumentReference = personCollectionRef.document(userId)
                            val user = mutableMapOf<String, Any>()
                            user["name"] = username
                            user["email"] = email
                            documentReference.set(user).addOnSuccessListener {
                                Timber.e("attemptRegistration: user profile is created for: + $userId")
                            }
                            setIsAuthenticated(isAuthenticated = true)
                        }
                        .addOnFailureListener {
                            Toast.makeText(appContext, "Błąd w rejestracji", Toast.LENGTH_LONG).show()
                        }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Timber.e("attemptRegistration: an error occurred")
                        Toast.makeText(appContext, "Błąd w rejestracji", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    fun attemptLogin(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            Log.d(TAG, "attemptLogin: successfully logged in a user ${firebaseAuth.currentUser?.displayName}")
                            setIsAuthenticated(isAuthenticated = true)
                        }
                        .addOnFailureListener {
                            Toast.makeText(appContext, "Błąd w logowaniu.", Toast.LENGTH_LONG).show()
                        }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Log.e(TAG, "attemptLogin: an error occurred")
                        Toast.makeText(appContext, "Błąd w logowaniu", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setIsAuthenticated(isAuthenticated: Boolean) {
        _isSignedIn.value = isAuthenticated
    }

}