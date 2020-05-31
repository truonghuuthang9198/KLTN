package com.example.kltn.screen.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowMoreDealModel(val id:Int,val tabId:Int,val imgBookSMDeal:Int,val titleBookSMDeal: String,val priceReducedSMDeal: Double,val priceSMDeal:Double): Parcelable