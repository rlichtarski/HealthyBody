package com.hackheroes.healthybody.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hackheroes.healthybody.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_launcher.*

@AndroidEntryPoint
class LauncherFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launcher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        choose_login_button.setOnClickListener {
            navLogin()
        }

        choose_register_button.setOnClickListener {
            navRegistration()
        }

        focusable_view.requestFocus()

    }

    private fun navRegistration() {
        findNavController().navigate(R.id.action_launcherFragment_to_registerFragment)
    }

    private fun navLogin() {
        findNavController().navigate(R.id.action_launcherFragment_to_loginFragment)
    }
}