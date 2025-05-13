package com.example.mediplus20

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.mediplus20.navigation.AppNavHost
import com.example.mediplus20.navigation.ROUTE_HOME
import com.example.mediplus20.navigation.ROUTE_SIGNUP
import com.example.mediplus20.ui.theme.Mediplus20Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mediplus20Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(startDestination = ROUTE_SIGNUP) // 👈 Home is the first screen
                }
            }
        }
    }
}
