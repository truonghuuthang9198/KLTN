package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName


data class AddFavoriteResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int
)
{
    data class Data(
        @SerializedName("khachHang")
        val khachHang: Any,
        @SerializedName("maKhachHang")
        val maKhachHang: String,
        @SerializedName("maSach")
        val maSach: String,
        @SerializedName("sach")
        val sach: Any,
        @SerializedName("trangThai")
        val trangThai: Any
    )
}
