package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("username")
    val username: String
)