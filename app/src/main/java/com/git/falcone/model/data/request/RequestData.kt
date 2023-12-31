package com.git.falcone.model.data.request

import com.google.gson.annotations.SerializedName

data class RequestData(
    @SerializedName("token") val token: String,
    @SerializedName("planet_names") val planetNames: List<String>,
    @SerializedName("vehicle_names") val vehicleNames: List<String>
)
