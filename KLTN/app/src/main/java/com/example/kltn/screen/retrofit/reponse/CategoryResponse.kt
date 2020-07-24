package com.example.kltn.screen.retrofit.reponse

import com.google.gson.annotations.SerializedName


data class CategoryResponse(
    @SerializedName("maDanhMuc")
    val maDanhMuc: String,
    @SerializedName("tenDanhMuc")
    val tenDanhMuc: String,
    @SerializedName("theLoais")
    val theLoais: List<TheLoai>
) {
    data class TheLoai(
        @SerializedName("danhMuc")
        val danhMuc: String?,
        @SerializedName("maDanhMuc")
        val maDanhMuc: String,
        @SerializedName("maTheLoai")
        val maTheLoai: String,
        @SerializedName("saches")
        val saches: String?,
        @SerializedName("tenTheLoai")
        val tenTheLoai: String
    )
}

