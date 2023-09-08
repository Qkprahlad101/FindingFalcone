package com.git.falcone.model.data.response

data class VehicleResponse(
    val name: String,
    val totalNumber: Int,
    val maxDistance: Int,
    val speed: Int
) {
    var remainingNumber: Int = totalNumber
}

val vehiclesData = listOf(
    VehicleResponse("Space pod", 2, 200, 2),
    VehicleResponse("Space rocket", 1, 300, 4),
    VehicleResponse("Space shuttle", 1, 400, 5),
    VehicleResponse("Space ship", 2, 600, 10)
)