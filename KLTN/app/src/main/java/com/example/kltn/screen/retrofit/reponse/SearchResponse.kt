package com.example.kltn.screen.retrofit.reponse

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("ghiChu")
    val ghiChu: String,
    @SerializedName("giaBan")
    val giaBan: Double,
    @SerializedName("giamGia")
    val giamGia: Double,
    @SerializedName("hinhAnh")
    val hinhAnh: String,
    @SerializedName("kichThuoc")
    val kichThuoc: String,
    @SerializedName("loaiBia")
    val loaiBia: String,
    @SerializedName("maCongTy")
    val maCongTy: String,
    @SerializedName("maNhaXuatBan")
    val maNhaXuatBan: Any,
    @SerializedName("maSach")
    val maSach: String,
    @SerializedName("maTacGia")
    val maTacGia: String,
    @SerializedName("maTheLoai")
    val maTheLoai: String,
    @SerializedName("ngayXuatBan")
    val ngayXuatBan: String,
    @SerializedName("soLuong")
    val soLuong: Int,
    @SerializedName("soTrang")
    val soTrang: String,
    @SerializedName("tenCongTy")
    val tenCongTy: String,
    @SerializedName("tenNhaXuatBan")
    val tenNhaXuatBan: String,
    @SerializedName("tenSach")
    val tenSach: String,
    @SerializedName("tenTacGia")
    val tenTacGia: String,
    @SerializedName("tenTheLoai")
    val tenTheLoai: String,
    @SerializedName("tinhTrang")
    val tinhTrang: String,
    @SerializedName("soSao")
    val soSao:Int
)