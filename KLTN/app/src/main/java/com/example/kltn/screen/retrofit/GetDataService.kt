package com.example.kltn.screen.retrofit

import com.example.kltn.screen.retrofit.reponse.CityReponse
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("city")
    fun getListCity(): Call<CityReponse>
    
}