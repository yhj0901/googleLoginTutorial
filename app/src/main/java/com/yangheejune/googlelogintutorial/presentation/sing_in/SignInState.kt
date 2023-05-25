package com.yangheejune.googlelogintutorial.presentation.sing_in

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
