package com.yogesh.retrofitkotlin.quotesAppWithRetrofit.model.models

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("q")
    val quote:String,
    @SerializedName("a")
    val author:String,
    val c:String,
    val h:String
    )

//data class _Quote_(
//    val _id: String,
//    val author: String,
//    val authorSlug: String,
//    val content: String,
//    val dateAdded: String,
//    val dateModified: String,
//    val length: Int,
//    val tags: List<String>
//)