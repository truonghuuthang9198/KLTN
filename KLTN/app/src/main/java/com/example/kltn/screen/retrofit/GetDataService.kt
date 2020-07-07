package com.example.kltn.screen.retrofit

import com.example.kltn.screen.retrofit.reponse.SachResponse
import com.example.kltn.screen.retrofit.reponse.CityResponse
import com.example.kltn.screen.retrofit.reponse.DistrictResponse
import com.example.kltn.screen.retrofit.reponse.WardResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetDataService {
    @GET("city")
    fun getListCity(): Call<CityResponse>
    @GET("Sach")
    fun getListSach(): Call<List<SachResponse>>
    @GET("city/{id}/district")
    fun getListDistrict(@Path("id") id: Int): Call<List<DistrictResponse>>
    @GET("district/{id}/ward")
    fun getListWard(@Path("id") id:Int):Call<List<WardResponse>>
}