package com.git.falcone.ui.composeUI

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.git.falcone.ui.FindFalconeViewModel
import android.widget.Spinner
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SpinnerScreen() {
    val viewModel: FindFalconeViewModel = viewModel()
    val data = viewModel.planetsLiveData.value ?: emptyList()
    val spinnerData = data.map { it.name } ?: emptyList()

    val selectedData1 = remember { mutableStateOf("") }
    val selectedData2 = remember { mutableStateOf("") }
    val selectedData3 = remember { mutableStateOf("") }
    val selectedData4 = remember { mutableStateOf("") }

    Column {
        Text("Selected items: ${selectedData1.value}, ${selectedData2.value}, ${selectedData3.value}, ${selectedData4.value}")

        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .width(200.dp)
                .clickable(onClick = { expanded = true })
                .background(Color.LightGray)
        ) {
            Text(
                text = if (selectedData1.value.isEmpty()) "Select an item" else selectedData1.value,
                modifier = Modifier.padding(16.dp)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                spinnerData.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedData1.value = item
                        expanded = false
                    }) {
                        Text(text = item)
                    }
                }
            }
        }

        var expanded2 by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .width(200.dp)
                .clickable(onClick = { expanded2 = true })
                .background(Color.LightGray)
        ) {
            Text(
                text = if (selectedData2.value.isEmpty()) "Select an item" else selectedData2.value,
                modifier = Modifier.padding(16.dp)
            )

            DropdownMenu(
                expanded = expanded2,
                onDismissRequest = { expanded2 = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                spinnerData.filter { it != selectedData1.value }.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedData2.value = item
                        expanded2 = false
                    }) {
                        Text(text = item)
                    }
                }
            }
        }

        var expanded3 by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .width(200.dp)
                .clickable(onClick = { expanded3 = true })
                .background(Color.LightGray)
        ) {
            Text(
                text = if (selectedData3.value.isEmpty()) "Select an item" else selectedData3.value,
                modifier = Modifier.padding(16.dp)
            )

            DropdownMenu(
                expanded = expanded3,
                onDismissRequest = { expanded3 = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                spinnerData.filter { it != selectedData1.value && it != selectedData2.value }.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedData3.value = item
                        expanded3 = false
                    }) {
                        Text(text = item)
                    }
                }
            }
        }

        var expanded4 by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .width(200.dp)
                .clickable(onClick = { expanded4 = true })
                .background(Color.LightGray)
        ) {
            Text(
                text = if (selectedData4.value.isEmpty()) "Select an item" else selectedData4.value,
                modifier = Modifier.padding(16.dp)
            )

            DropdownMenu(
                expanded = expanded4,
                onDismissRequest = { expanded4 = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                spinnerData.filter { it != selectedData1.value && it != selectedData2.value && it != selectedData3.value }.forEach { item ->
                    DropdownMenuItem(onClick = {
                        selectedData4.value = item
                        expanded4 = false
                    }) {
                        Text(text = item)
                    }
                }
            }
        }

        Button(
            onClick = {
                viewModel.postData(
                    selectedData1.value,
                    selectedData2.value,
                    selectedData3.value,
                    selectedData4.value
                )
            }
        ) {
            Text("Submit")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getPlanets()
    }
}
