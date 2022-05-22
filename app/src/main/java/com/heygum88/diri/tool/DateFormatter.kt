package com.heygum88.diri.tool

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateFormatter {
    private val dateFormat = "yyyy/MM/dd/hh/mm/ss"
    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat(dateFormat)

    fun millsToDate(mills: Long): String {
        return formatter.format(mills)?:""
    }
}