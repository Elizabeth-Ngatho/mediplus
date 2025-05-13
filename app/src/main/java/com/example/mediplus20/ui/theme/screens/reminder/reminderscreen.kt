package com.example.mediplus20.ui.theme.screens.reminder


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediplus20.ui.theme.MediPlus20Theme
import com.example.mediplus20.ui.theme.screens.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderScreen(navController: NavController) {
    val reminders = remember { mutableStateListOf("Doctor Appointment - 15th May at 2:00 PM") }
    var showDialog by remember { mutableStateOf(false) }
    var newReminderText by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Appointment Reminders") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (reminders.isEmpty()) {
                Text("No reminders yet.", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(reminders) { reminder ->
                        ReminderCard(reminder)
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newReminderText.isNotBlank()) {
                                reminders.add(newReminderText)
                                newReminderText = ""
                                showDialog = false
                            }
                        }
                    ) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                },
                title = { Text("New Reminder") },
                text = {
                    OutlinedTextField(
                        value = newReminderText,
                        onValueChange = { newReminderText = it },
                        label = { Text("Reminder details") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            )
        }
    }
}

@Composable
fun ReminderCard(reminder: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        tonalElevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = reminder,
            modifier = Modifier.padding(12.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
@Preview(showBackground = true)
@Composable
private fun ReminderScreenPreview() {
    MediPlus20Theme(darkTheme = true) {
        ReminderScreen(navController = rememberNavController())
    }
}
