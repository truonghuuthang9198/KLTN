package com.example.kltn.screen.retrofit

import com.example.kltn.screen.retrofit.reponse.CityReponse
import com.example.kltn.screen.retrofit.reponse.SachReponse
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("city")
    fun getListCity(): Call<CityReponse>
    @GET("Sach")
    fun getListSach(): Call<List<SachReponse>>
    
}