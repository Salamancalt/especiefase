package com.example.senfase

import com.example.consumokotlinsimple.models.LogIn
import com.example.consumokotlinsimple.models.ResponseLogIn
import com.example.prueba.models.DefaultResponse
import com.example.prueba.models.DefaultResponseObject
import com.example.prueba.models.User.UserResult
import com.example.prueba.models.especie.EspecieResult
import com.example.prueba.models.fase.RequerimientoAnimal
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    fun postLogin(@Body logIn: LogIn): Call<ResponseLogIn>

    @GET("viewUser/{id}")
    fun getUser(@Path("id") id : String): Call<DefaultResponseObject>

    @GET("listEspecie")
    fun getEspecies(): Call<DefaultResponse<EspecieResult>>

    @GET("alimentestatus/{id}")
    fun getFaseByIdEspecie(@Path("id") id : String): Call<DefaultResponse<RequerimientoAnimal>>
}