package com.example.kltn.screen.retrofit.reponse
import com.google.gson.annotations.SerializedName




class ReviewResponse : ArrayList<ReviewResponse.ReviewResponseItem>()
{
    data class ReviewResponseItem(
        @SerializedName("baSao")
        val baSao: Int,
        @SerializedName("bonSao")
        val bonSao: Int,
        @SerializedName("haiSao")
        val haiSao: Int,
        @SerializedName("motSao")
        val motSao: Int,
        @SerializedName("namSao")
        val namSao: Int,
        @SerializedName("nhanXets")
        val nhanXets: List<NhanXet>
    )
    {
        data class NhanXet(
            @SerializedName("hoTen")
            val hoTen: String,
            @SerializedName("maNhanXet")
            val maNhanXet: String,
            @SerializedName("maSach")
            val maSach: String,
            @SerializedName("ngayDang")
            val ngayDang: String,
            @SerializedName("noiDung")
            val noiDung: String,
            @SerializedName("sach")
            val sach: Any,
            @SerializedName("soSao")
            val soSao: Int
        )
    }
}
