package com.git.falcone.ui.composeUI

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.git.falcone.ui.FindFalconeViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.git.falcone.model.data.request.Planets
import com.git.falcone.model.data.request.RequestData
import com.git.falcone.model.data.request.Vehicles
import com.git.falcone.model.data.response.AuthKeyResponse
import com.git.falcone.model.data.response.PlanetsResponse
import com.git.falcone.model.data.response.VehicleResponse
import com.git.falcone.model.data.response.vehiclesData
import org.json.JSONArray
import org.json.JSONObject


@Composable
fun MainScreen() {
    val viewModel: FindFalconeViewModel = viewModel()
    val planetsData = viewModel.planetsLiveData.value ?: emptyList()
    val vehiclesData = listOf(
        VehicleResponse("Space pod", 2, 200, 2),
        VehicleResponse("Space rocket", 1, 300, 4),
        VehicleResponse("Space shuttle", 1, 400, 5),
        VehicleResponse("Space ship", 2, 600, 10)
    )
    val authKey: AuthKeyResponse = viewModel.authKeyLiveData.value ?: AuthKeyResponse(key = "")

    val selectedPlanet1 = remember { mutableStateOf<PlanetsResponse?>(null) }
    val selectedVehicle1 = remember { mutableStateOf<VehicleResponse?>(null) }
    val numberOfSelectedVehicle1 = remember { mutableStateOf(vehiclesData[0].totalNumber) }
    val numberOfSelectedVehicle2 = remember { mutableStateOf(vehiclesData[1].totalNumber) }
    val numberOfSelectedVehicle3 = remember { mutableStateOf(vehiclesData[2].totalNumber) }
    val numberOfSelectedVehicle4 = remember { mutableStateOf(vehiclesData[3].totalNumber) }
    val selectedPlanet2 = remember { mutableStateOf<PlanetsResponse?>(null) }
    val selectedVehicle2 = remember { mutableStateOf<VehicleResponse?>(null) }
    val selectedPlanet3 = remember { mutableStateOf<PlanetsResponse?>(null) }
    val selectedVehicle3 = remember { mutableStateOf<VehicleResponse?>(null) }
    val selectedPlanet4 = remember { mutableStateOf<PlanetsResponse?>(null) }
    val selectedVehicle4 = remember { mutableStateOf<VehicleResponse?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Selected items: ${selectedPlanet1.value?.name ?: ""}, ${selectedPlanet2.value?.name ?: ""}, ${selectedPlanet3.value?.name ?: ""}, ${selectedPlanet4.value?.name ?: ""}",
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colors.secondary
            )

            Dropdown(
                selectedPlanet1,
                planetsData.filter { it !== selectedPlanet2.value && it !== selectedPlanet3.value && it !== selectedPlanet4.value },
                "Select a Planet"
            )
            RadioGroup(selectedVehicle1, vehiclesData, "Vehicle 1", numberOfSelectedVehicle1)

            Dropdown(
                selectedPlanet2,
                planetsData.filter { it !== selectedPlanet1.value && it !== selectedPlanet3.value && it !== selectedPlanet4.value },
                "Select another Planet"
            )
            RadioGroup(selectedVehicle2, vehiclesData, "Vehicle 2", numberOfSelectedVehicle2)

            Dropdown(
                selectedPlanet3,
                planetsData.filter { it !== selectedPlanet1.value && it !== selectedPlanet2.value && it !== selectedPlanet4.value },
                "Select a third Planet"
            )
            RadioGroup(selectedVehicle3, vehiclesData, "Vehicle 3", numberOfSelectedVehicle3)

            Dropdown(
                selectedPlanet4,
                planetsData.filter { it !== selectedPlanet1.value && it !== selectedPlanet2.value && it !== selectedPlanet3.value },
                "Select a fourth Planet"
            )
            RadioGroup(selectedVehicle4, vehiclesData, "Vehicle 4", numberOfSelectedVehicle4)

            Button(
                onClick = {

                    val planets = listOf(
                        selectedPlanet1.value?.name,
                        selectedPlanet2.value?.name,
                        selectedPlanet3.value?.name,
                        selectedPlanet4.value?.name
                    )
                    val vehicles = listOf(
                        selectedVehicle1.value?.name,
                        selectedVehicle2.value?.name,
                        selectedVehicle3.value?.name,
                        selectedVehicle4.value?.name
                    )


                    val convertedJson = JSONObject().apply {
                        put("token", authKey.key)
                        put("planet_names", JSONArray(planets))
                        put("vehicle_names", JSONArray(vehicles))
                    }

                    val convertedJsonString = convertedJson.toString()
                    viewModel.findQueen(convertedJsonString)

                }
            ) {
                Text("Find Queen!")
            }

            Text(
                text = viewModel.queenLiveData.value.toString() ?: "",
                modifier = Modifier.padding(top = 24.dp)
            )

            LaunchedEffect(Unit) {
                viewModel.getAuthKey()
                viewModel.getPlanets()
                viewModel.getVehicles()
            }
        }
    }
}

@Composable
fun RadioGroup(
    selectedVehicle: MutableState<VehicleResponse?>,
    vehiclesData: List<VehicleResponse>,
    label: String,
    numberOfSelectedVehicle: MutableState<Int>
) {
    Column {
        Text(
            text = label,
            color = MaterialTheme.colors.onSecondary
        )
        vehiclesData.forEach { vehicle ->
            if (vehicle.totalNumber > 0) {
                Row {
                    RadioButton(
                        selected = selectedVehicle.value === vehicle,
                        onClick = {
                            // Increase total number of previously selected vehicle
                            selectedVehicle.value?.let { prevSelected ->
                                if (prevSelected != vehicle) {
                                    prevSelected.totalNumber += 1
                                    numberOfSelectedVehicle.value += 1
                                }
                            }
                            // Update selected vehicle
                            selectedVehicle.value = vehicle
                            // Update total number of selected vehicle in all dropdowns
                            vehiclesData.forEach { dropdownVehicle ->
                                if (dropdownVehicle != vehicle) {
                                    dropdownVehicle.totalNumber += 1
                                }
                            }
                            vehicle.totalNumber -= 1
                            numberOfSelectedVehicle.value -= 1
                        }
                    )
                    Text(text = "${vehicle.name} (${vehicle.totalNumber} left)")
                }
            } else {
                Row {
                    RadioButton(
                        selected = false,
                        enabled = false,
                        onClick = {}
                    )
                    Text(text = "${vehicle.name} (0 left)", color = Color.Gray)
                }
            }
        }
        DisposableEffect(selectedVehicle.value) {
            val previousVehicle = selectedVehicle.value
            onDispose {
                previousVehicle?.totalNumber?.plus(1)?.let {
                    previousVehicle.totalNumber = it
                    numberOfSelectedVehicle.value -= 1
                }
            }
        }
    }
}


@Composable
fun Dropdown(
    selectedData: MutableState<PlanetsResponse?>,
    spinnerData: List<PlanetsResponse>,
    label: String
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .width(200.dp)
            .clickable(onClick = { expanded = true })
            .background(Color.LightGray)
    ) {
        Text(
            text = if (selectedData.value == null) label else selectedData.value!!.name,
            modifier = Modifier.padding(16.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            spinnerData.filter { it !== selectedData.value }.forEach { item ->
                DropdownMenuItem(onClick = {
                    selectedData.value = item
                    expanded = false
                }) {
                    Text(text = item.name)
                }
            }
        }
    }
}
