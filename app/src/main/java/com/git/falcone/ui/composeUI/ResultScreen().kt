package com.git.falcone.ui.composeUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.git.falcone.ui.FindFalconeViewModel

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: FindFalconeViewModel
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val result = viewModel.queenLiveData.value?.planetName
            Text(
                text = if (viewModel.queenLiveData.value?.status != "Queen not Found!!") "Queen was found at $result" else "$result",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = if (viewModel.queenLiveData.value?.status != "Queen not Found!!") "Time Taken to find Queen: ${viewModel.timeTaken.value}" else "",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = { navController.popBackStack() }
            ) {
                Text("Go Back")
            }
        }
    }
}
