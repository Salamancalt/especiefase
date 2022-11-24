package com.example.prueba.models.User

data class UserResult(
    var id_usuario:Number,
    var contrasena:String,
    var nombres:String,
    var correo:String,
    var celular:Number,
    var identificacion:Int,
    var ubicacion:String,
    var foto :String,
    var tipo_usuario:String,
    var region_id: Int,
    var createdAt:String,
    var updatedAt:String
)
