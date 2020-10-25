package com.hackheroes.healthybody.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hackheroes.healthybody.R
import com.hackheroes.healthybody.util.CustomMarkerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_graph.*
import javax.inject.Inject

@AndroidEntryPoint
class GraphFragment : Fragment() {

    @Inject
    lateinit var mAuth: FirebaseAuth

    private val mainViewModel: MainViewModel by viewModels()

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

        back_arrow_graph.setOnClickListener {
            findNavController().navigate(R.id.action_graphFragment_to_dashboardFragment)
        }

        see_your_meals_card_view.setOnClickListener {
            findNavController().navigate(R.id.action_graphFragment_to_mealsFragment)
        }

        subscribeObservers()
        setupBarChart()
    }

    private fun subscribeObservers() {
        mainViewModel.getUserData.observe(viewLifecycleOwner, Observer { user ->
            your_weight_value.text = "${user.weight} kg"
            your_height_value.text = "${user.height} cm"
        })

        mainViewModel.runsSortedByDate.observe(viewLifecycleOwner, Observer {
            it?.let {
                val allAvgCaloriesBurned = it.indices.map { i -> BarEntry(i.toFloat(), it[i].caloriesBurned) }
                val bardataSet = BarDataSet(allAvgCaloriesBurned, "spalone kalorie z ostatniej sesji vs " +
                        "średnia spalonych kalorii z wszystkich sesji").apply {
                    valueTextColor = Color.WHITE
                    color = ContextCompat.getColor(requireContext(), R.color.bar_chart_accent)
                }
                barChart.data = BarData(bardataSet)
                barChart.marker = CustomMarkerView(it.reversed(), requireContext(), R.layout.marker_view)
                barChart.invalidate()
            }
        })

    }

    private fun setupBarChart() {
        barChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            setDrawLabels(false)
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }
        barChart.axisLeft.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }
        barChart.axisRight.apply {
            axisLineColor = Color.WHITE
            textColor = Color.WHITE
            setDrawGridLines(false)
        }
        barChart.apply {
            description.text = "spalone kalorie z ostatniej sesji vs średnia spalonych kalorii z wszystkich sesji"
            description.textColor = Color.WHITE
            legend.isEnabled = false
        }
    }

}