package com.git.falcone.model.data.response

import com.google.gson.annotations.SerializedName

data class PlanetsResponse(
    @SerializedName("name") val name: String,
    @SerializedName("distance") val distance: Int
)
