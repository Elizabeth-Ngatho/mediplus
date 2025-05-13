package com.example.mediplus20.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediplus20.ui.theme.screens.appointment.AppointmentScreen
import com.example.mediplus20.ui.theme.screens.doctor.DoctorScreen
import com.example.mediplus20.ui.theme.screens.home.HomeScreen
import com.example.mediplus20.ui.theme.screens.hospital.HospitalsScreen
import com.example.mediplus20.ui.theme.screens.login.LoginScreen
import com.example.mediplus20.ui.theme.screens.reminder.ReminderScreen
import com.example.mediplus20.ui.theme.screens.signup.SignupScreen

@Composable
fun AppNavHost(startDestination: String = ROUTE_LOGIN) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        composable(ROUTE_LOGIN) { LoginScreen(navController) }
        composable(ROUTE_SIGNUP) { SignupScreen(navController) }
        composable(ROUTE_HOME) { HomeScreen(navController) }
        composable(ROUTE_APPOINTMENT) { AppointmentScreen(navController) }
        composable(ROUTE_HOSPITALS) { HospitalsScreen(navController) }
        composable(ROUTE_DOCTOR) { DoctorScreen(navController) }
        composable(ROUTE_REMINDER) { ReminderScreen(navController) }
    }
}
