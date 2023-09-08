package com.git.falcone.model.data.response

import com.google.gson.annotations.SerializedName

data class VehicleResponse(
   @SerializedName("name") val name: String,
   @SerializedName("total_no") val totalNumber: Int,
   @SerializedName("max_distance") val maxDistance: Int,
   @SerializedName("speed") val speed: Int
) {
    var remainingNumber: Int = totalNumber
}

val vehiclesData = listOf(
    VehicleResponse("Space pod", 2, 200, 2),
    VehicleResponse("Space rocket", 1, 300, 4),
    VehicleResponse("Space shuttle", 1, 400, 5),
    VehicleResponse("Space ship", 2, 600, 10)
)