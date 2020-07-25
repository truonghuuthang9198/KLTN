package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName


data class DeleteAddressResponse(
    @SerializedName("data")
    val data: String?,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int
)