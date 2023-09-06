package com.git.falcone.model.data.response

import com.google.gson.annotations.SerializedName

data class AuthKeyResponse(
    @SerializedName("token") val key: String
)
