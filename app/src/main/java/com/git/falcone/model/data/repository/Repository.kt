package com.git.falcone.model.data.repository

import android.util.Log
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
import retrofit2.http.Headers
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPlanets(): Flow<List<PlanetsResponse>> {

        return flow {
            val response = apiService.getPlanets()
            try{
                when(response.code()){
                    200 -> {
                        if (response.isSuccessful) {
                            val planets = response.body() ?: emptyList()
                            emit(planets)
                        } else {
                            val statusCode = response.code()
                            throw Exception("Failed to find planets, Status code: $statusCode")
                        }
                    }
                    400 -> throw Exception("400 Bad Request!${response.body().toString()}")
                    404 -> throw Exception("404 Not Found! ${response.body().toString()}")
                    else -> throw Exception("Failed to get Planets, Status code: ${response.code()}")
                }
            }catch (e: Exception) {
                throw Exception("Error: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getVehicles(): Flow<List<VehicleResponse>> {
        return flow {
            val response = apiService.getVehicles()
            try {
                when (response.code()) {
                    200 -> {
                        if (response.isSuccessful) {
                            val vehicles = response.body() ?: emptyList()
                            emit(vehicles)
                        } else {
                            val statusCode = response.code()
                            throw Exception("Failed to find vehicles, Status code: $statusCode")
                        }
                    }
                    400 -> throw Exception("400 Bad Request!${response.body().toString()}")
                    404 -> throw Exception("404 Not Found! ${response.body().toString()}")
                    else -> throw Exception("Failed to get Vehicles, Status code: ${response.code()}")
                }
            } catch (e: Exception) {
                throw Exception("Error: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAuthKey(): Flow<AuthKeyResponse> {
        return flow {
            val response = apiService.getAuthKey()
            try {
                when (response.code()) {
                    200 -> {
                        if (response.isSuccessful) {
                            val key = response.body() ?: AuthKeyResponse(key = "")
                            emit(key)
                        } else {
                            val statusCode = response.code()
                            throw Exception("Failed to get Auth key, Status code: $statusCode")
                        }
                    }
                    400 -> throw Exception("400 Bad Request!${response.body().toString()}")
                    404 -> throw Exception("404 Not Found! ${response.body().toString()}")
                    else -> throw Exception("Failed to get Auth key, Status code: ${response.code()}")
                }
            } catch (e: Exception) {
                throw Exception("Error: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }


    fun findQueen(request: RequestData): Flow<FoundQueenResponse?> {
        return flow {
            val response = apiService.findQueen(request)
            try {
                when (response.code()) {
                    200 -> {
                        val found = response.body()
                        if (found?.status == "success" && found.planetName != null) {
                            emit(found)
                        } else {
                            throw Exception("Queen was not found in the selected planets!")
                        }
                    }
                    400 -> throw Exception("400 Bad Request!${response.body().toString()}")
                    404 -> throw Exception("404 Not Found! ${response.body().toString()}")
                    else -> throw Exception("Failed to find Queen, Status code: ${response.code()}")
                }
            } catch (e: Exception) {
                throw Exception("Error: ${e.message}")
            }
        }.flowOn(Dispatchers.IO)
    }

}