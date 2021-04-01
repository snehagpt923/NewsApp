package com.example.newsapp.utils

object Utility {

    const val BASE_URL = "https://storage.googleapis.com/carousell-interview-assets/android/"

    sealed class SortBy {
        object Popular : SortBy()
        object Recent : SortBy()
        object Default : SortBy()
    }

    const val POPULAR = "Popular"
    const val RECENT = "Recent"

    fun getReadableTime(creationTime: Long): String {
        val difference = System.currentTimeMillis() - creationTime
        val differenceHours = difference / (60 * 60 * 1000)
        val differenceDays = differenceHours / 24
        return if (differenceHours < 24) {
            "$differenceHours hours ago"
        } else {
            "$differenceDays days ago"
        }
    }
}