package com.example.kltn.screen.firebase

data class HoaDonModel(var MaHD:String, var MaKH: String,var dateBill:String, var TongTien:Double,var diachi:String, var CTHD:ArrayList<ChiTietHDModel>)
{
    constructor() : this("","","",0.0,"",CTHD = ArrayList<ChiTietHDModel>())
    {

    }

    fun HoaDonModel(MaHD:String, MaKH: String,dateBill: String, TongTien:Double,diachi:String, CTHD:ArrayList<ChiTietHDModel>) {
        this.MaHD = MaHD
        this.MaKH = MaKH
        this.dateBill =dateBill
        this.TongTien = TongTien
        this.diachi = diachi
        this.CTHD = CTHD
    }
}