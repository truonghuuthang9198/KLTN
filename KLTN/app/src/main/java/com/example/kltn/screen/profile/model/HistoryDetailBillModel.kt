package com.example.kltn.screen.profile.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HistoryDetailBillModel(
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
    val soTrang: String,
    val tenSach: String,
    val tinhTrang: String,
    val soSao:Int,
    val donGia:Double,
    val soLuong: Int,
    val giaGiamDS:Double = giaban*(1.00-giamGia)
) : Parcelable