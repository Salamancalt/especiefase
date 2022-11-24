package com.example.prueba.core

import com.example.consumokotlinsimple.models.ResponseLogIn
import com.example.prueba.models.Fase
import com.example.prueba.models.fase.RequerimientoAnimal

object Constants {
    var actualUser : ResponseLogIn ?= null
    const val URL = " http://152.200.173.74:8057/api/"
    var faseActual = RequerimientoAnimal(
        0,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        0,
        "",
    )
    var cantidadMezcla = 100
}