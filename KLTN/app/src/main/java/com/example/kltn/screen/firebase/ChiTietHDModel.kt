package com.example.kltn.screen.firebase

data class ChiTietHDModel(val maCTHD:String,val maSP:String,val soLuong:Int,val donGia:Double)
{
    constructor(): this("","",0,0.0)
}