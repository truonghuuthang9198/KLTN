package com.example.kltn.screen.retrofit.reponse

import com.google.gson.annotations.SerializedName


data class CityResponse(
    @SerializedName("LtsItem")
    val ltsItem: List<LtsItem>,
    @SerializedName("TotalDoanhNghiep")
    val totalDoanhNghiep: Int
) {
    data class LtsItem(
        @SerializedName("Created")
        val created: Any,
        @SerializedName("ID")
        val iD: Int,
        @SerializedName("STT")
        val sTT: Int,
        @SerializedName("SolrID")
        val solrID: String,
        @SerializedName("Title")
        val title: String,
        @SerializedName("TotalDoanhNghiep")
        val totalDoanhNghiep: Int,
        @SerializedName("Type")
        val type: Int,
        @SerializedName("Updated")
        val updated: Any
    )
}

