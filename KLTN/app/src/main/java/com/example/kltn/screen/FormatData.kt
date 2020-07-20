package com.example.kltn.screen

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*

class FormatData{
    companion object
    {
        fun formatMoneyVND(value: Double): String {
            val myFormatter = DecimalFormat("###,###,###", DecimalFormatSymbols(Locale.ITALIAN))
            return myFormatter.format(value).plus(" đ")
        }
        fun formatMoney(value: Double): String {
            val myFormatter = DecimalFormat("###,###,###", DecimalFormatSymbols(Locale.ITALIAN))
            return myFormatter.format(value)
        }
        fun convertDateFormat(
            inputDate: String,
            fromFormat: SimpleDateFormat,
            toFormat : SimpleDateFormat
        ): String {
            if (inputDate.isEmpty()) return ""
            return try {
                val toDate = fromFormat.parse(inputDate)
                toFormat.format(toDate)
            } catch (ex: java.lang.Exception) {
                ""
            }
        }
    }
}
