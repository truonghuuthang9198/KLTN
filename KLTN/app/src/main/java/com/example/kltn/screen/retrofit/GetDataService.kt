package com.example.kltn.screen.retrofit

import com.example.kltn.screen.retrofit.model.*
import com.example.kltn.screen.retrofit.reponse.*
import retrofit2.Call
import retrofit2.http.*

interface GetDataService {
    @GET("city")
    fun getListCity(): Call<CityResponse>

    @GET("Sach")
    fun getListSach(): Call<List<SachResponse>>

    @GET("city/{id}/district")
    fun getListDistrict(@Path("id") id: Int): Call<List<DistrictResponse>>

    @GET("district/{id}/ward")
    fun getListWard(@Path("id") id: Int): Call<List<WardResponse>>

    @POST("NguoiDung/authenticate")
    fun login(@Body loginModel: LoginModel): Call<LoginResponse>

    @GET("NguoiDung")
    fun getUserWithToken(@Header("Authorization") token: String): Call<CheckLoginResponse>

    @POST("KhachHang")
    fun registerUser(@Body registerModel: RegisterModel): Call<RegisterResponse>

    @GET("Search/{id}")
    fun getListSearch(@Path("id") id: String): Call<List<SearchResponse>>

    @GET("YeuThich")
    fun getListFavorite(@Header("Authorization") token: String): Call<List<FavoriteResponse>>

    @POST("YeuThich")
    fun addBookFavorite(@Header("Authorization") token: String,@Body addFavoriteRequest: AddFavoriteRequest): Call<AddFavoriteResponse>

    @DELETE("YeuThich")
    fun deleteFavorite(@Header("Authorization") token: String,@Query("id") id:String,@Query("id_masach") id_masach:String): Call<DeleteFavoriteResponse>

    @GET("LichSuMuaHang")
    fun getListHistory(@Header("Authorization") token: String): Call<List<HistoryResponse>>

    @GET("SoDiaChi")
    fun getListAddress(@Header("Authorization") token: String): Call<List<ManangerAddressResponse>>

    @POST("SoDiaChi")
    fun addAddressNew(@Header("Authorization") token: String,@Body addAddressModel: AddAddressModel):Call<AddAddressResponse>

    @PUT("SoDiaChi")
    fun updateAddress(@Header("Authorization") token: String,@Body addAddressModel: AddAddressModel):Call<UpdateAddressResponse>

    @GET("DanhMuc")
    fun getListCategory(): Call<List<CategoryResponse>>

    @DELETE("SoDiaChi")
    fun deleteAddress(@Header("Authorization") token: String,@Query("id") id:String): Call<DeleteAddressResponse>





}