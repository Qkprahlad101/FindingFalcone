package com.git.falcone.ui.composeUI.UiComponents

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object ResultScreen : Screen("result_screen")
}


