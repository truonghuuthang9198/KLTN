package com.example.kltn.screen.suggest.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SuggestModel(val imgBookSuggest:Int,val titleBookSuggest: String,val priceReducedSuggest: Double,val priceSuggest:Double,val starBookSuggest:Int):
    Parcelable