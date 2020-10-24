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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hackheroes.healthybody.R
import com.hackheroes.healthybody.services.TrackingService
import com.hackheroes.healthybody.ui.auth.AuthViewModel
import com.hackheroes.healthybody.util.Constants.Companion.ACTION_START_OR_RESUME_SERVICE
import com.hackheroes.healthybody.util.Constants.Companion.REQUEST_CODE_LOCATION_PERMISSION
import com.hackheroes.healthybody.util.TrackingUtility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment(), EasyPermissions.PermissionCallbacks {

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
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        userId = mAuth.currentUser?.uid!!

        start_run_icon.setOnClickListener {
            sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
        }

        graph_card_view.setOnClickListener{
            findNavController().navigate(R.id.action_dashboardFragment_to_graphFragment)
        }

        subscribeObservers()
    }

    private fun subscribeObservers() {
        mainViewModel.fetchUserData()

        mainViewModel.getUserData.observe(viewLifecycleOwner, Observer { user ->
            bmi_value.text = user.bmi
            username_text_view.text = "Witaj ${user.name}"
            kcal_need_value.text = "${user.bmr} kcal"
        })
    }

    private fun sendCommandToService(action: String) =
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }

    private fun requestPermissions() {
        if(TrackingUtility.hasLocationPermissions(requireContext())) {
            return
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
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
        if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
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