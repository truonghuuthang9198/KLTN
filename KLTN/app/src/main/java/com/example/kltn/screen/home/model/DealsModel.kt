package com.example.kltn.screen.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DealsModel(val id:Int,val tabId:Int,val imgBookDeal:String,val titleBookDeal: String,val priceReduced: Double,val price:Double):Parcelable