package com.git.falcone.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.git.falcone.model.Repository
import com.git.falcone.model.data.apiService.ApiService
import com.git.falcone.model.data.request.RequestData
import com.git.falcone.model.data.response.AuthKeyResponse
import com.git.falcone.model.data.response.FoundQueenResponse
import com.git.falcone.model.data.response.PlanetsResponse
import com.git.falcone.model.data.response.VehicleResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class FindFalconeViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private lateinit var planetsLiveData: MutableLiveData<List<PlanetsResponse>>
    private lateinit var vehiclesLiveData: MutableLiveData<List<VehicleResponse>>
    private lateinit var authKeyLiveData: MutableLiveData<Any>
    private lateinit var queenLiveData: MutableLiveData<FoundQueenResponse>

    fun getAuthKey(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getAuthKey().collect{
                    authKeyLiveData.value = it
                }
            }catch (e: Exception){

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

            }
        }
    }

    fun findQueen(request: RequestData){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.findQueen(request).collect{
                    queenLiveData.value = it
                }
            }catch (e: Exception){

            }
        }
    }
}