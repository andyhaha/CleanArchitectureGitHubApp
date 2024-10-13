package com.github.app.common

import android.annotation.SuppressLint
import android.util.Log

@SuppressLint("DefaultLocale")
fun Int.formatToK(): String {
    val value = this
    return if (value >= 1000) {
        val formattedValue = value / 1000
        Log.d("FormattedValue", "this = $value, " +
                "formattedValue = ${value % 1000}")
        val modules = value % 1000
        if (modules < 50) {
            // No decimal part needed, return integer (e.g., 4k)
            "${formattedValue}k"
        } else {
            // Decimal part exists, return float (e.g., 4.5k)
            var format = String.format("%.1fk", value / 1000.0)
            if (format.endsWith(".0k")) {
                format = format.replace(".0k", "k")
            }
            format
        }
    } else {
        value.toString()
    }
}

