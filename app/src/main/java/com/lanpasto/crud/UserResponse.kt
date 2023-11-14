package com.lanpasto.crud

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") var id: Int?,
    @SerializedName("email") var email: String? = "",
    @SerializedName("first_name") var firstname: String? = "",
    @SerializedName("last_name") var lastname: String? = "",
    @SerializedName("avatar") var avatar: String? = "",


    )


