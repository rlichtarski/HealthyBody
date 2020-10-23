package com.hackheroes.healthybody.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hackheroes.healthybody.R
import com.hackheroes.healthybody.ui.auth.AuthViewModel
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

    private lateinit var mainViewModel: MainViewModel

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

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        meal_icon.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_mealsFragment)
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        mainViewModel.fetchUserData()

        mainViewModel.getUserData.observe(viewLifecycleOwner, Observer { user ->
            bmi_value.text = user.bmi
            username_text_view.text = "Witaj ${user.name}"
            kcal_need_value.text = user.bmr
        })
    }

}