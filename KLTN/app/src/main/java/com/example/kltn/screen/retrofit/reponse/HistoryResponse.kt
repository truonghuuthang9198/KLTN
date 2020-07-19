package com.example.kltn.screen.retrofit.reponse

import com.google.gson.annotations.SerializedName




data class HistoryResponse(
    @SerializedName("chiTietHoaDons")
    val chiTietHoaDons: List<ChiTietHoaDon>,
    @SerializedName("maHoaDon")
    val maHoaDon: String,
    @SerializedName("maKhachHang")
    val maKhachHang: String,
    @SerializedName("ngayLap")
    val ngayLap: String,
    @SerializedName("thanhTien")
    val thanhTien: Double
)
{
    data class ChiTietHoaDon(
        @SerializedName("donGia")
        val donGia: Double,
        @SerializedName("hoaDon")
        val hoaDon: String?,
        @SerializedName("maHoaDon")
        val maHoaDon: String,
        @SerializedName("maSach")
        val maSach: String,
        @SerializedName("sach")
        val sach: Sach,
        @SerializedName("soLuong")
        val soLuong: Int
    )

    data class Sach(
        @SerializedName("chiTietHoaDons")
        val chiTietHoaDons: String?,
        @SerializedName("congTyPhatHanh")
        val congTyPhatHanh: CongTyPhatHanh,
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
        val maNhaXuatBan: String,
        @SerializedName("maSach")
        val maSach: String,
        @SerializedName("maTacGia")
        val maTacGia: String,
        @SerializedName("maTheLoai")
        val maTheLoai: String,
        @SerializedName("ngayXuatBan")
        val ngayXuatBan: String,
        @SerializedName("nhaXuatBan")
        val nhaXuatBan: NhaXuatBan,
        @SerializedName("soLuong")
        val soLuong: Int,
        @SerializedName("soSao")
        val soSao: Int,
        @SerializedName("soTrang")
        val soTrang: String,
        @SerializedName("tacGia")
        val tacGia: TacGia,
        @SerializedName("tenSach")
        val tenSach: String,
        @SerializedName("theLoai")
        val theLoai: TheLoai,
        @SerializedName("tinhTrang")
        val tinhTrang: String,
        @SerializedName("yeuThiches")
        val yeuThiches: String?
    )

    data class CongTyPhatHanh(
        @SerializedName("diaChi")
        val diaChi: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("maCongTy")
        val maCongTy: String,
        @SerializedName("saches")
        val saches: String?,
        @SerializedName("soDienThoai")
        val soDienThoai: String,
        @SerializedName("tenCongTy")
        val tenCongTy: String
    )

    data class NhaXuatBan(
        @SerializedName("diaChi")
        val diaChi: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("maNhaXuatBan")
        val maNhaXuatBan: String,
        @SerializedName("saches")
        val saches: String?,
        @SerializedName("soDienThoai")
        val soDienThoai: String,
        @SerializedName("tenNhaXuatBan")
        val tenNhaXuatBan: String
    )

    data class TacGia(
        @SerializedName("diaChi")
        val diaChi: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("maTacGia")
        val maTacGia: String,
        @SerializedName("saches")
        val saches: String?,
        @SerializedName("soDienThoai")
        val soDienThoai: String,
        @SerializedName("tenTacGia")
        val tenTacGia: String
    )

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

