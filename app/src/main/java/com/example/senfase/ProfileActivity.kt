package com.example.senfase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.prueba.core.Constants
import com.example.prueba.models.DefaultResponseObject
import com.example.senfase.databinding.ActivityProfileBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        obtainUser()
        clicks()

    }

    private fun obtainUser() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var servicios: ApiService = retrofit.create(ApiService::class.java)
        servicios.getUser(Constants.actualUser!!.userForToken.id.toString())
            .enqueue(object : Callback<DefaultResponseObject> {
                override fun onResponse(
                    call: Call<DefaultResponseObject>,
                    response: Response<DefaultResponseObject>
                ) {
                    binding.textView4.text = response.body()!!.results.nombres
                }

                override fun onFailure(Call: Call<DefaultResponseObject>, t: Throwable) {
                    Log.e("onFailure: ", t.toString())
                    Snackbar.make(binding.root, "Error de servidor", Snackbar.LENGTH_SHORT).show()
                }
            })
    }

    private fun clicks() {
        binding.lLBack.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
    }
}