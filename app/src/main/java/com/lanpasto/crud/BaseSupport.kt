package com.lanpasto.crud

import com.google.gson.annotations.SerializedName

data class BaseSupport<T> (
    @SerializedName("data")
    var data: T?=null
)
