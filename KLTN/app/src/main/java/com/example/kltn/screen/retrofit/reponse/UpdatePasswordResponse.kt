package com.example.kltn.screen.retrofit.reponse

import com.google.gson.annotations.SerializedName


data class UpdatePasswordResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status_code")
    val statusCode: Int
) {
    data class Data(
        @SerializedName("capDoThanhVien")
        val capDoThanhVien: Int,
        @SerializedName("diaChi")
        val diaChi: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("gioiTinh")
        val gioiTinh: String,
        @SerializedName("maKhachHang")
        val maKhachHang: String,
        @SerializedName("matKhau")
        val matKhau: String,
        @SerializedName("soDienThoai")
        val soDienThoai: String,
        @SerializedName("tenKhachHang")
        val tenKhachHang: String
    )
}
