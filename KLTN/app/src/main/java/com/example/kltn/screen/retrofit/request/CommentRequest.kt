package com.example.kltn.screen.retrofit.request

import java.time.format.DateTimeFormatter
import java.util.*

data class CommentRequest(
    val MaSach: String,
    val HoTen:String,
    val SoSao:Int,
    val NoiDung:String,
    val NgayDang: Date
)