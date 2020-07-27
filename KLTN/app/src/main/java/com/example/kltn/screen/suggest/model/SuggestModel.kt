package com.example.kltn.screen.suggest.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "suggest_table")
data class SuggestModel(
    @PrimaryKey
    @ColumnInfo(name = "maTL")
    val maTL: String
) : Parcelable