package com.hackheroes.healthybody.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.hackheroes.healthybody.R
import com.hackheroes.healthybody.ui.BmiViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    @Inject
    lateinit var mAuth: FirebaseAuth

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_button.setOnClickListener {
            register()
        }

        focusable_view.requestFocus()
    }

    private fun register() {
        val username: String = register_input_username.text.toString()
        val email: String = register_input_email.text.toString()
        val password: String = register_input_password.text.toString()
        val confirm_password: String = register_input_confirm_password.text.toString()

        if(!password.equals(confirm_password)) {
            Toast.makeText(context , "hasła się nie zgadzają", Toast.LENGTH_SHORT).show()
        }


        if(password.length > 6 && confirm_password.length > 6) {
            authViewModel.register(username, email, password)
        } else {
            Toast.makeText(requireActivity(), "hasło nie może mieć mniej niż 6 znaków", Toast.LENGTH_SHORT).show()
        }

    }
}