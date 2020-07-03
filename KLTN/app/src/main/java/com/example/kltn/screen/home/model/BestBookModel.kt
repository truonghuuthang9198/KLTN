package com.example.kltn.screen.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BestBookModel(val id:Int,val tabId:Int,val imgBestBook:Int,val titleBestBook: String,val priceReducedBB: Double,val priceBB:Double):
    Parcelable