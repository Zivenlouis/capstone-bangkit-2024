package com.capstoneproject.auxilium.helper

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

object FormatterUtil {

    fun formatWeight(weight: String?): String {
        return weight?.toFloatOrNull()?.let { "${it.toInt()} g" } ?: ""
    }

    fun formatMemory(memory: String?): String {
        return memory?.let { "$it GB" } ?: ""
    }

    fun formatCamera(camera: String?): String {
        return camera?.toFloatOrNull()?.let { "${it.toInt()} MP" } ?: ""
    }

    fun formatBattery(battery: String?): String {
        return battery?.let { "$it mAh" } ?: ""
    }

    fun formatCharging(charging: String?): String {
        return charging?.toFloatOrNull()?.let { "${it.toInt()} W" } ?: ""
    }

    fun formatYesNo(value: String?): String {
        return when (value?.uppercase(Locale.getDefault())) {
            "TRUE" -> "Yes"
            "FALSE" -> "No"
            else -> ""
        }
    }

    fun formatDate(releaseDate: String?): String {
        return releaseDate?.let {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(it)
            date?.let { it1 -> outputFormat.format(it1) }
        } ?: ""
    }

    fun formatResolution(resolution: String?): String {
        return resolution?.let {
            val parts = it.split("x")
            if (parts.size == 2) {
                "${parts[0]} x ${parts[1]} pixels"
            } else {
                it // Return original string if format is unexpected
            }
        } ?: ""
    }

    fun formatPrice(price: String?, countryCode: String = "ID"): String {
        return try {
            val priceLong = price?.toLongOrNull()
            if (priceLong != null) {
                val locale = if (countryCode.equals("ID", ignoreCase = true)) Locale("id", "ID") else Locale.US
                val format = NumberFormat.getCurrencyInstance(locale)
                if (locale == Locale("id", "ID")) {
                    format.format(priceLong).replace("Rp", "Rp.")
                } else {
                    format.format(priceLong)
                }
            } else {
                price.orEmpty()
            }
        } catch (e: Exception) {
            price.orEmpty()
        }
    }
}
