package com.git.falcone.ui.composeUI.UiComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

//@Composable
//fun Dropdown(
//    selectedData: MutableState<String>,
//    spinnerData: List<String>,
//    label: String
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    Box(
//        modifier = Modifier
//            .width(200.dp)
//            .clickable(onClick = { expanded = true })
//            .background(Color.LightGray)
//    ) {
//        Text(
//            text = if (selectedData.value.isEmpty()) label else selectedData.value,
//            modifier = Modifier.padding(16.dp)
//        )
//
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            spinnerData.filter { it != selectedData.value }.forEach { item ->
//                DropdownMenuItem(onClick = {
//                    selectedData.value = item
//                    expanded = false
//                }) {
//                    Text(text = item)
//                }
//            }
//        }
//    }
//}