package com.hackheroes.healthybody.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hackheroes.healthybody.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    @Inject
    lateinit var mAuth: FirebaseAuth

    private lateinit var userId: String

    val personCollectionRef = Firebase.firestore.collection("users")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userId = mAuth.currentUser?.uid!!

        meal_icon.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_mealsFragment)
        }

        getDataFromFirestore()
    }

    private fun getDataFromFirestore() {
        val documentReference: DocumentReference = personCollectionRef.document(userId)
        documentReference.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                return@addSnapshotListener

            }

            querySnapshot?.let {
                bmi_value.setText(it.getString("bmi"))
                username_text_view.setText("Witaj ${it.getString("name")}")
                val weight = it.getString("weight")
                val height = it.getString("height")
                val age = it.getString("age")
                val sex = it.getString("sex")
                kcalNeed(weight?.toDouble(), height?.toDouble(), age?.toDouble(), sex)
            }

        }
    }

    private fun kcalNeed(weight: Double?, height: Double?, age: Double?, sex: String?) {
        var caloriesNeed: Double

        caloriesNeed = when {
            sex.equals("m") -> {
                (66 + (13.7 * weight!!) + (5 * height!!) - (6.8 * age!!))
            }
            sex.equals("f") -> {
                655 + (9.6 * weight!!) + (1.8 * height!!) - (4.7 * age!!)
            }
            else -> {
                0.0
            }
        }

        kcal_need_value.setText("${caloriesNeed.toString()} kcal")

    }


}