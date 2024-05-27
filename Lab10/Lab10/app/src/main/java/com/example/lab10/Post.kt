package com.example.lab10

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("id")
    var id: Int,
    @SerializedName("userId")
    var userId: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("body")
    var body: String,
)