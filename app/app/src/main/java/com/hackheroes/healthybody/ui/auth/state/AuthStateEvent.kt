package com.hackheroes.healthybody.ui.auth.state

sealed class AuthStateEvent {

    class LoginAttemptEvent(
        val email: String,
        val password: String
    ): AuthStateEvent()

    class RegistrationAttemptEvent(
        val email: String,
        val username: String,
        val password: String,
        val confirm_password: String
    ): AuthStateEvent()

    class CheckPreviousAuthEvent: AuthStateEvent()
}