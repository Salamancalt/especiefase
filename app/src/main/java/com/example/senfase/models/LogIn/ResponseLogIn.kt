package com.example.consumokotlinsimple.models

import com.example.prueba.models.LogIn.UserForToken

data class ResponseLogIn(
    val userForToken: UserForToken,
    val token: String,
)