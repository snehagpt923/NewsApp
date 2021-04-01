package com.example.newsapp.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NewsNetworkEntity(

    @SerializedName("id")
    @Expose
    var id: Long,

    @SerializedName("title")
    @Expose
    var title: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("banner_url")
    @Expose
    var bannerUrl: String,

    @SerializedName("time_created")
    @Expose
    var timeCreated: Long,

    @SerializedName("rank")
    @Expose
    var rank: Int
)



