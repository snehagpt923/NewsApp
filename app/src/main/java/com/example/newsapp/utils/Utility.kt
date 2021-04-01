package com.example.newsapp.utils

object Utility {

    const val BASE_URL = "https://storage.googleapis.com/carousell-interview-assets/android/"

    sealed class SortBy {
        object Popular : SortBy()
        object Recent : SortBy()
    }

    fun getReadableTime(creationTime: Long): String {
        val secondsDifference = (System.currentTimeMillis() / 1000) - creationTime
        val minuteDifference = secondsDifference / (60)
        val hourDifference = minuteDifference / 60
        val daysDifference = hourDifference / 24
        val monthDifference = daysDifference / 30
        val yearDifference = monthDifference / 12

        return when {
            secondsDifference < 60 -> "$secondsDifference seconds ago"
            minuteDifference < 60 -> "$minuteDifference minutes ago"
            hourDifference < 24 -> "$hourDifference hours ago"
            daysDifference < 30 -> "$daysDifference days ago"
            monthDifference < 12 -> "$monthDifference months ago"
            else -> "$yearDifference years ago"
        }
    }
}