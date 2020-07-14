package com.example.kltn.screen.cart.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "cart_table")
data class CartModel(
    @PrimaryKey
    @ColumnInfo(name = "maSach")
    val maSach:String,
    @ColumnInfo(name = "tenSach")
    val tenSach: String,
    @ColumnInfo(name = "soLuong")
    var soLuong: Int,
    @ColumnInfo(name= "giaTien")
    val giaTien: Double,
    @ColumnInfo(name ="image")
    val image: String
) : Parcelable