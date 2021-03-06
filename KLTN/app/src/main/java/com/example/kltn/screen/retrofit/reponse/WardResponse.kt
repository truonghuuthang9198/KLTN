package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName


data class WardResponse(
    @SerializedName("Created")
    val created: Any,
    @SerializedName("ID")
    val iD: Int,
    @SerializedName("QuanHuyenID")
    val quanHuyenID: Int,
    @SerializedName("QuanHuyenTitle")
    val quanHuyenTitle: String,
    @SerializedName("QuanHuyenTitleAscii")
    val quanHuyenTitleAscii: String,
    @SerializedName("STT")
    val sTT: Int,
    @SerializedName("SolrID")
    val solrID: String,
    @SerializedName("TinhThanhID")
    val tinhThanhID: Int,
    @SerializedName("TinhThanhTitle")
    val tinhThanhTitle: String,
    @SerializedName("TinhThanhTitleAscii")
    val tinhThanhTitleAscii: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Type")
    val type: Int,
    @SerializedName("Updated")
    val updated: Any
)