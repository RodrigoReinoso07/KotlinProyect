package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("Characters")
    fun getCharacters() : Call<List<Character>>
}

