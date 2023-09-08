package com.git.falcone.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.git.falcone.ui.composeUI.MainScreen
import com.git.falcone.ui.composeUI.ResultScreen
import dagger.hilt.android.AndroidEntryPoint
import com.git.falcone.ui.composeUI.Screen

@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindFalconeApp(applicationContext)
        }
    }
}

@Composable
fun FindFalconeApp(applicationContext: Context) {
    val viewModel: FindFalconeViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModel, applicationContext)
        }
        composable(Screen.ResultScreen.route) {
            ResultScreen(navController = navController, viewModel)
        }
    }
}
