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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hackheroes.healthybody.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_graph.*
import javax.inject.Inject

@AndroidEntryPoint
class GraphFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userId = mAuth.currentUser?.uid!!

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        back_arrow_graph.setOnClickListener {
            findNavController().navigate(R.id.action_graphFragment_to_dashboardFragment)
        }

        see_your_meals_card_view.setOnClickListener {
            findNavController().navigate(R.id.action_graphFragment_to_mealsFragment)
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        mainViewModel.getUserData.observe(viewLifecycleOwner, Observer { user ->
            your_weight_value.text = "${user.weight} kg"
            your_height_value.text = "${user.height} cm"
        })

    }

}