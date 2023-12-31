package com.git.falcone.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.falcone.model.data.repository.Repository
import com.git.falcone.model.data.request.RequestData
import com.git.falcone.model.data.response.AuthKeyResponse
import com.git.falcone.model.data.response.FoundQueenResponse
import com.git.falcone.model.data.response.PlanetsResponse
import com.git.falcone.model.data.response.VehicleResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FindFalconeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){

    val planetsLiveData = mutableStateOf<List<PlanetsResponse>>(emptyList())
    val vehiclesLiveData = mutableStateOf<List<VehicleResponse>>(emptyList())
    val authKeyLiveData = mutableStateOf<AuthKeyResponse?>(null)
    val queenLiveData = mutableStateOf<FoundQueenResponse?>(null)
    var timeTaken = mutableStateOf(0)

    fun getAuthKey(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getAuthKey().collect{
                    authKeyLiveData.value = it
                }
            }catch (e: Exception){
                authKeyLiveData.value = AuthKeyResponse(key = "")
            }
        }
    }

    fun getPlanets(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getPlanets().collect{
                    planetsLiveData.value = it
                }
            }catch (e: Exception){
                planetsLiveData.value = emptyList()
            }
        }
    }

    fun getVehicles(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getVehicles().collect{
                    vehiclesLiveData.value = it
                }
            }catch (e: Exception){
                vehiclesLiveData.value = emptyList()
            }
        }
    }

    fun findQueen(request: RequestData): Flow<FoundQueenResponse> {
        return flow {
            try {
                repository.findQueen(request).collect {
                    queenLiveData.value = it ?: FoundQueenResponse(planetName = "", status = "Queen Not Found!!")
                }
            } catch (e: Exception) {
                queenLiveData.value = FoundQueenResponse(planetName = e.message, status = "Queen not Found!!")
            }
            queenLiveData.value?.let { emit(it) }
        }
    }

}