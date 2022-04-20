package com.taran.retrofitproject

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class retrofit {

    var retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://gorest.co.in/public/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var retrofitInterface = retrofitBuilder.create(RetrofitInterface::class.java)

}