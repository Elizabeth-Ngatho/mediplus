package com.example.mediplus20.ui.theme.screens.appointment

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediplus20.ui.theme.MediPlus20Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppointmentScreen(navController: NavController) {
    val context = LocalContext.current

    var fullName by remember { mutableStateOf("") }
    var department by remember { mutableStateOf("") }
    var doctor by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var symptoms by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Book Appointment") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = department,
                onValueChange = { department = it },
                label = { Text("Department (e.g. Cardiology)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = doctor,
                onValueChange = { doctor = it },
                label = { Text("Doctor Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Date (e.g. 2025-05-20)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                label = { Text("Time (e.g. 14:00)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            OutlinedTextField(
                value = symptoms,
                onValueChange = { symptoms = it },
                label = { Text("Describe Symptoms") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                maxLines = 4
            )

            Button(
                onClick = {
                    Toast.makeText(context, "Appointment booked successfully!", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Book Appointment")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppointmentScreenPreview() {
    MediPlus20Theme(darkTheme = true){
    AppointmentScreen(navController = rememberNavController())
}
}