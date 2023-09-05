package com.git.falcone.model.data.repository

import com.git.falcone.model.data.apiService.ApiService
import com.git.falcone.model.data.request.RequestData
import com.git.falcone.model.data.response.AuthKeyResponse
import com.git.falcone.model.data.response.FoundQueenResponse
import com.git.falcone.model.data.response.PlanetsResponse
import com.git.falcone.model.data.response.VehicleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPlanets(): Flow<List<PlanetsResponse>> {

        return flow {
            val response = apiService.getPlanets()
            if (response.isSuccessful) {
                val planets = response.body() ?: emptyList()
                emit(planets)
            } else {
                val statusCode = response.code()
                throw Exception("Failed to find planets, Status code: $statusCode")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getVehicles(): Flow<List<VehicleResponse>> {
        return flow {
            val response = apiService.getVehicles()
            if (response.isSuccessful) {
                val vehicles = response.body() ?: emptyList()
                emit(vehicles)
            } else {
                val statusCode = response.code()
                throw Exception("Failed to find vehicles, Status code: $statusCode")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAuthKey(): Flow<AuthKeyResponse?> {
        return flow {
            val response = apiService.getAuthKey()
            if (response.isSuccessful) {
                val key = response.body() ?: AuthKeyResponse( key = "")
                emit(key)
            } else {
                val statusCode = response.code()
                throw Exception("Failed to get Auth key, Status code: $statusCode")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun findQueen(request: RequestData): Flow<FoundQueenResponse?> {
        return flow {
            val response = apiService.findQueen(request)
            if (response.isSuccessful) {
                val found = response.body() ?: throw Exception("Failed to find Queen")
                emit(found)
            } else {
                val statusCode = response.code()
                throw Exception("Failed to find Queen, Status code: $statusCode")
            }
        }.flowOn(Dispatchers.IO)
    }
}