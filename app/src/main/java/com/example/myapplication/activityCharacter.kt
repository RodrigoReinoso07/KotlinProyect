package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.util.JsonReader
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.StringReader

class activityCharacter : AppCompatActivity() {

    private lateinit var retrofit: Retrofit
    private lateinit var apiService: APIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        lifecycleScope.launch (Dispatchers.Main){


            withContext(Dispatchers.IO){
                retrofit = Retrofit.Builder()
                    .baseUrl("https://thronesapi.com/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                apiService = retrofit.create(APIService::class.java)

                val call: Call<List<Character>> = apiService.getCharacters()
                call.enqueue(object : Callback<List<Character>> {
                    override fun onResponse(call: Call<List<Character>>, response: Response<List<Character>>) {
                        if (response.isSuccessful) {
                            val characters: List<Character>? = response.body()
                            if (characters != null) {
                                for (character in characters) {

                                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                                    val adapter = activity_character.CustomAdapter(characters)
                                    recyclerView.layoutManager = LinearLayoutManager( this@activityCharacter)
                                    recyclerView.adapter = adapter


                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Character>>, t: Throwable) {
                        println("Error: ${t.message}")
                    }
                })
            }
        }

    }
}


