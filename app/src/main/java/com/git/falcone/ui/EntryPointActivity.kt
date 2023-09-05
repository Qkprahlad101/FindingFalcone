package com.git.falcone.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.git.falcone.ui.composeUI.SpinnerScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPointActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            SpinnerScreen()
            MainScreen()
        }
    }

    @Composable
    fun MainScreen(){
        SpinnerScreen()
    }
}
