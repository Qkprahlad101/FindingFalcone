package com.git.falcone.model.data.apiService

import com.git.falcone.model.data.request.RequestData
import com.git.falcone.model.data.response.AuthKeyResponse
import com.git.falcone.model.data.response.FoundQueenResponse
import com.git.falcone.model.data.response.PlanetsResponse
import com.git.falcone.model.data.response.VehicleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Accept: application/json")
    @POST("token")
    suspend fun getAuthKey(): Response<AuthKeyResponse>

    @GET("vehicles")
    suspend fun getVehicles(): Response<List<VehicleResponse>>

    @GET("planets")
    suspend fun getPlanets(): Response<List<PlanetsResponse>>

    @POST("find")
    suspend fun findQueen(
        @Body request: RequestData
    ): Response<FoundQueenResponse>
}