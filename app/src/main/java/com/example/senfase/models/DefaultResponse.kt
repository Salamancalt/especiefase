package com.example.prueba.models

import com.example.prueba.models.especie.EspecieResult

data class DefaultResponse<T>(
    var status:String,
    var action:String,
    var show:Boolean,
    var message:String,
    var delay: String,
    var code:String,
    var results: List<T> = listOf()
)
