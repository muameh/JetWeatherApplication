package com.mehmetbaloglu.jetweatherforecast.utils
import com.mehmetbaloglu.jetweatherforecast.data.model.FiveDaysForecast.ListItem
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


object Utils {


    fun formatDayOfWeek(dtTxt: String): String {
        // OpenWeatherMap API'sinden gelen tarih formatı
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

        // Gün ismini kısaltmak için istenen format
        val outputFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)

        // Gelen string tarihini LocalDateTime olarak parse ediyoruz
        val date = LocalDateTime.parse(dtTxt, inputFormatter)

        // İstenen formatta (günün ilk üç harfi) String olarak geri döndür
        return date.format(outputFormatter)
    }


    fun getUniqueDays(listItems: List<ListItem?>?): List<ListItem> {
        // Bir set kullanarak sadece benzersiz günleri alıyoruz
        val uniqueDays = mutableSetOf<String>()
        val result = mutableListOf<ListItem>()

        listItems?.forEach { item ->
            // Tarihi sadece gün bilgisi olarak alıyoruz
            val day = item?.dtTxt?.substring(0, 10)  // "yyyy-mm-dd hh:mm:ss" formatında, sadece "yyyy-mm-dd" kısmı alınır

            if (day != null && !uniqueDays.contains(day)) {
                uniqueDays.add(day)
                result.add(item)  // O güne ait ilk tahmini ekliyoruz
            }
        }
        return result
    }


    fun formatDate(dtTxt: String): String {
        // OpenWeatherMap API'sinden gelen tarih formatı
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

        // İstenen tarih formatı
        val outputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH)

        // Gelen string tarihini LocalDateTime olarak parse ediyoruz
        val date = LocalDateTime.parse(dtTxt, inputFormatter)

        // İstenen formatta String olarak geri döndür
        return date.format(outputFormatter)
    }

}