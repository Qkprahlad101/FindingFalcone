package com.git.falcone.ui.composeUI

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.git.falcone.model.data.request.RequestData
import com.git.falcone.model.data.response.AuthKeyResponse
import com.git.falcone.model.data.response.PlanetsResponse
import com.git.falcone.model.data.response.VehicleResponse
import com.git.falcone.ui.composeUI.UiComponents.Screen
import kotlinx.coroutines.launch


@Composable
fun MainScreen(
    navController: NavController,
    viewModel: FindFalconeViewModel,
    applicationContext: Context
) {
    val planetsData = viewModel.planetsLiveData.value
    val vehiclesData = listOf(
        VehicleResponse("Space pod", 2, 200, 2),
        VehicleResponse("Space rocket", 1, 300, 4),
        VehicleResponse("Space shuttle", 1, 400, 5),
        VehicleResponse("Space ship", 2, 600, 10)
    )
    val authKey: AuthKeyResponse = viewModel.authKeyLiveData.value ?: AuthKeyResponse(key = "")
    val selectedPlanet1 = remember { mutableStateOf<PlanetsResponse?>(null) }
    val selectedVehicle1 = remember { mutableStateOf<VehicleResponse?>(null) }

    val selectedPlanet2 = remember { mutableStateOf<PlanetsResponse?>(null) }
    val selectedVehicle2 = remember { mutableStateOf<VehicleResponse?>(null) }

    val selectedPlanet3 = remember { mutableStateOf<PlanetsResponse?>(null) }
    val selectedVehicle3 = remember { mutableStateOf<VehicleResponse?>(null) }

    val selectedPlanet4 = remember { mutableStateOf<PlanetsResponse?>(null) }
    val selectedVehicle4 = remember { mutableStateOf<VehicleResponse?>(null) }

    val remainingQuantities1 =
        remember { mutableStateListOf(*vehiclesData.map { it.totalNumber }.toTypedArray()) }
    val remainingQuantities2 =
        remember { mutableStateListOf(*vehiclesData.map { it.totalNumber }.toTypedArray()) }
    val remainingQuantities3 =
        remember { mutableStateListOf(*vehiclesData.map { it.totalNumber }.toTypedArray()) }
    val remainingQuantities4 =
        remember { mutableStateListOf(*vehiclesData.map { it.totalNumber }.toTypedArray()) }

    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Gray)
            ) {
                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Find Falcone",
                        style = MaterialTheme.typography.h5
                    )
                    Text(
                        text = "Reset /",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = "GeekTrust Home",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.secondary
                    )

                }
            }

            Text(
                text = "Selected Planets: ${selectedPlanet1.value?.name ?: ""}, ${selectedPlanet2.value?.name ?: ""}, ${selectedPlanet3.value?.name ?: ""}, ${selectedPlanet4.value?.name ?: ""}",
                modifier = Modifier.padding(bottom = 8.dp),
                color = MaterialTheme.colors.secondary
            )

            Dropdown(
                selectedPlanet1,
                planetsData.filter { it !== selectedPlanet2.value && it !== selectedPlanet3.value && it !== selectedPlanet4.value },
                "Select a Planet"
            )
            RadioGroup(
                selectedVehicle1,
                vehiclesData,
                "Vehicle 1",
                0,
                remainingQuantities1,
                selectedPlanet1.value
            )

            Dropdown(
                selectedPlanet2,
                planetsData.filter { it !== selectedPlanet1.value && it !== selectedPlanet3.value && it !== selectedPlanet4.value },
                "Select another Planet"
            )
            RadioGroup(
                selectedVehicle2,
                vehiclesData,
                "Vehicle 2",
                1,
                remainingQuantities2,
                selectedPlanet2.value
            )

            Dropdown(
                selectedPlanet3,
                planetsData.filter { it !== selectedPlanet1.value && it !== selectedPlanet2.value && it !== selectedPlanet4.value },
                "Select a third Planet"
            )
            RadioGroup(
                selectedVehicle3,
                vehiclesData,
                "Vehicle 3",
                2,
                remainingQuantities3,
                selectedPlanet3.value
            )

            Dropdown(
                selectedPlanet4,
                planetsData.filter { it !== selectedPlanet1.value && it !== selectedPlanet2.value && it !== selectedPlanet3.value },
                "Select a fourth Planet"
            )
            RadioGroup(
                selectedVehicle4,
                vehiclesData,
                "Vehicle 4",
                3,
                remainingQuantities4,
                selectedPlanet4.value
            )

            Button(
                onClick = {
                    val allFieldsSelected = selectedPlanet1.value != null &&
                            selectedPlanet2.value != null &&
                            selectedPlanet3.value != null &&
                            selectedPlanet4.value != null &&
                            selectedVehicle1.value != null &&
                            selectedVehicle2.value != null &&
                            selectedVehicle3.value != null &&
                            selectedVehicle4.value != null

                    if (allFieldsSelected) {
                        val request = RequestData(
                            authKey.key,
                            listOf(
                                selectedPlanet1.value?.name ?: "",
                                selectedPlanet2.value?.name ?: "",
                                selectedPlanet3.value?.name ?: "",
                                selectedPlanet4.value?.name ?: ""
                            ),
                            listOf(
                                selectedVehicle1.value?.name ?: "",
                                selectedVehicle2.value?.name ?: "",
                                selectedVehicle3.value?.name ?: "",
                                selectedVehicle4.value?.name ?: ""
                            )
                        )

                        scope.launch {
                            viewModel.findQueen(request).collect { response ->
                                val planetName = response.planetName
                                if (planetName != null) {
                                    val planet = planetsData.find { it.name == planetName }
                                    if (planet != null) {
                                        val vehicle =
                                            vehiclesData.find { it.name == selectedVehicle1.value?.name }
                                                ?: vehiclesData.find { it.name == selectedVehicle2.value?.name }
                                                ?: vehiclesData.find { it.name == selectedVehicle3.value?.name }
                                                ?: vehiclesData.find { it.name == selectedVehicle4.value?.name }
                                        if (vehicle != null) {
                                            viewModel.timeTaken.value = planet.distance / vehicle.speed
                                        }
                                    }
                                }
                                viewModel.queenLiveData.value = response
                            }
                        }
                        navController.navigate(Screen.ResultScreen.route)
                    } else {
                        Toast.makeText(applicationContext, "Please select all fields!!", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text("Find Queen!")
            }

            LaunchedEffect(Unit) {
                viewModel.getAuthKey()
                viewModel.getPlanets()
                viewModel.getVehicles()
            }

            // Footer
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Gray)
            ) {
                    Text(
                        text = "Coding Problem - www.geektrust.in/finding-falcone"
                    )
            }
        }
    }
}

@Composable
fun RadioGroup(
    selectedVehicle: MutableState<VehicleResponse?>,
    vehiclesData: List<VehicleResponse>,
    label: String,
    vehicleIndex: Int,
    remainingQuantities: MutableList<Int>,
    selectedPlanet: PlanetsResponse?
) {
    Column {
        Text(
            text = label,
            color = MaterialTheme.colors.onSecondary
        )

        vehiclesData.forEachIndexed { index, vehicle ->
            val isVehicleSelectable = (selectedPlanet?.distance ?: 0) <= vehicle.maxDistance

            if (remainingQuantities[index] > 0 && isVehicleSelectable) {
                Row {
                    RadioButton(
                        selected = selectedVehicle.value === vehicle,
                        onClick = {
                            selectedVehicle.value = vehicle

                            remainingQuantities[vehicleIndex] -= 1
                            remainingQuantities[index] += 1
                        }
                    )
                    Text(text = "${vehicle.name} (${remainingQuantities[index]} left)")
                }
            } else {
                Row {
                    RadioButton(
                        selected = false,
                        enabled = false,
                        onClick = {}
                    )
                    Text(
                        text = "${vehicle.name} (0 left)",
                        color = if (isVehicleSelectable) Color.Black else Color.Gray
                    )
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
