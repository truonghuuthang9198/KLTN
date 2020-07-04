package com.example.kltn.screen.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookModel(
    val id: Int,
    val tabId: Int,
    val ghiChu: String,
    val giaban: Double,
    val giamGia: Double,
    val hinhAnh: String,
    val kichThuoc: String,
    val loaiBia: String,
    val maCongTy: String,
    val maSach: String,
    val maTacGia: String,
    val maTheLoai: String,
    val ngayXuatBan: String,
    val maNhaXuatBan: String,
    val soLuong: Int,
    val soTrang: String,
    val tenSach: String,
    val tinhTrang: String,
    val giaGiamDS:Double = giaban*(1.00-giamGia)
) : Parcelable

