package com.ravish.paging3_mvvm_kotlin.model

import com.squareup.moshi.Json

data class Dogs(
    @Json(name = "url")
    val url:String
)
