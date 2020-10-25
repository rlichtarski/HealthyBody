package com.hackheroes.healthybody.ui.main

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hackheroes.healthybody.R
import com.hackheroes.healthybody.db.Run
import com.hackheroes.healthybody.services.Polyline
import com.hackheroes.healthybody.services.TrackingService
import com.hackheroes.healthybody.services.TrackingService.Companion.pathPoints
import com.hackheroes.healthybody.ui.auth.AuthViewModel
import com.hackheroes.healthybody.util.Constants.Companion.ACTION_PAUSE_SERVICE
import com.hackheroes.healthybody.util.Constants.Companion.ACTION_START_OR_RESUME_SERVICE
import com.hackheroes.healthybody.util.Constants.Companion.ACTION_STOP_SERVICE
import com.hackheroes.healthybody.util.Constants.Companion.REQUEST_CODE_LOCATION_PERMISSION
import com.hackheroes.healthybody.util.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import timber.log.Timber
import java.lang.Math.round
import java.util.*
import javax.inject.Inject
import kotlin.math.roundToInt

@AndroidEntryPoint
class DashboardFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    @Inject
    lateinit var mAuth: FirebaseAuth

    private val mainViewModel: MainViewModel by viewModels()

    private var isTracking = false
    private var pathPoints = mutableListOf<Polyline>()

    private lateinit var userId: String

    private var curTimeInMillis = 0L

    private lateinit var userWeight: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        userId = mAuth.currentUser?.uid!!

        toggle_run_card_view.setOnClickListener {
            toggleRun()
        }

        cancel_run.setOnClickListener {
            showCancelTrackingDialog()
        }

        finish_run.setOnClickListener {
            endRunAndSaveToDb()
        }

        logout_button.setOnClickListener {
            showLogoutDialog()
        }

        graph_card_view.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_graphFragment)
        }

        subscribeObservers()
    }

    private fun showLogoutDialog() {
        val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Wylogować się?")
            .setMessage("Czy na pewno anulować bieżącą sesję i usunąć wszystkie jej dane?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Tak") { _, _ ->
                if(isTracking) {
                    stopRun()
                }
                mainViewModel.logout()

            }
            .setNegativeButton("Nie") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
        dialog.show()
    }

    private fun subscribeObservers() {
        mainViewModel.fetchUserData()

        mainViewModel.getUserData.observe(viewLifecycleOwner, Observer { user ->
            bmi_value.text = user.bmi
            username_text_view.text = "Witaj ${user.name}"
            kcal_need_value.text = "${user.bmr} kcal"
            userWeight = user.weight!!
        })

        TrackingService.isTracking.observe(viewLifecycleOwner, Observer {
            updateTracking(it)
        })

        TrackingService.pathPoints.observe(viewLifecycleOwner, Observer {
            pathPoints = it
        })

        mainViewModel.getLatestRunByDate().observe(viewLifecycleOwner, Observer { run ->
            setLatestRunValues(run)
        })

        TrackingService.timeRunInMillis.observe(viewLifecycleOwner, Observer {
            curTimeInMillis = it
            val formattedTime = TrackingUtility.getFormattedStopWatchTime(curTimeInMillis, true)
            if (curTimeInMillis > 0L) timer_card_view.visibility = View.VISIBLE
            timer.text = formattedTime
        })
    }

    private fun setLatestRunValues(run: Run?) {
        burned_calories_value.text = "${run?.caloriesBurned.toString()} kcal"
        highest_velocity_value.text = "${run?.avgSpeedInKMH.toString()} km/h"
        walked_distance_value.text = "${run?.distanceInMeters.toString()} m"
    }

    private fun toggleRun() {
        if (isTracking) {
            timer_card_view.visibility = View.VISIBLE
            sendCommandToService(ACTION_PAUSE_SERVICE)
        } else {
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }
    }

    private fun showCancelTrackingDialog() {
        val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Anulować tę sesję?")
            .setMessage("Czy na pewno anulować bieżącą sesję i usunąć wszystkie jej dane?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Tak") { _, _ ->
                stopRun()
            }
            .setNegativeButton("Nie") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            .create()
        dialog.show()
    }

    private fun stopRun() {
        timer_card_view.visibility = View.GONE
        sendCommandToService(ACTION_STOP_SERVICE)
    }

    private fun updateTracking(isTracking: Boolean) {
        this.isTracking = isTracking
        if (!isTracking) {
            start_run_icon.text = "Zacznij chodzić"
            finish_run.visibility = View.VISIBLE
        } else {
            start_run_icon.text = "Stop"
            timer_card_view.visibility = View.GONE
            finish_run.visibility = View.GONE
        }
    }

    private fun endRunAndSaveToDb() {
        var distanceInMeters = 0
        for (polyline in pathPoints) {
            distanceInMeters += TrackingUtility.calculatePolylineLength(polyline).toInt()
        }
        val avgSpeed =
            ((distanceInMeters / 1000f) / (curTimeInMillis / 1000f / 60 / 60) * 10).roundToInt() / 10f
        val dateTimestamp = Calendar.getInstance().timeInMillis
        val caloriesBurned = ((distanceInMeters / 1000f) * userWeight.toFloat()).toInt()
        val run =
            Run(dateTimestamp, avgSpeed, distanceInMeters, curTimeInMillis, caloriesBurned)
        mainViewModel.insertRun(run)
        Timber.d("run saved successfully")
        stopRun()
    }

    private fun sendCommandToService(action: String) =
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }

    private fun requestPermissions() {
        if (TrackingUtility.hasLocationPermissions(requireContext())) {
            return
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            EasyPermissions.requestPermissions(
                this,
                "Aby korzystać z tej aplikacji, musisz zaakceptować uprawnienia do lokalizacji.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        } else {
            EasyPermissions.requestPermissions(
                this,
                "Aby korzystać z tej aplikacji, musisz zaakceptować uprawnienia do lokalizacji.",
                REQUEST_CODE_LOCATION_PERMISSION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            )
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this)
                .setPositiveButton("Ok")
                .setNegativeButton("Anuluj")
                .build().show()
        } else {
            requestPermissions()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {}

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}