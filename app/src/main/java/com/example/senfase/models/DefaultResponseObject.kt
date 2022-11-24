package com.example.prueba.models

import com.example.prueba.models.User.UserResult


data class DefaultResponseObject(
    var status:String,
    var action:String,
    var show:Boolean,
    var message:String,
    var delay: String,
    var code:String,
    var results: UserResult
)
