package com.git.falcone.model.data.request

data class RequestData(
    val token: String,
    val planet_names: Planets,
    val vehicles: Vehicles
)

data class Planets(
    val name: String
)

data class Vehicles(
    val name: String
)