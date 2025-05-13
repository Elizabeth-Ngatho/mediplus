package com.example.mediplus20.ui.theme.screens.doctor

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediplus20.R
import com.example.mediplus20.ui.theme.MediPlus20Theme

data class Doctor(val name: String, val specialty: String, val imageRes: Int)

val dummyDoctors = listOf(
    Doctor("Dr. James Smith", "Cardiologist",com.example.mediplus20.R.drawable.wow1),
    Doctor("Dr. Lisa Brown", "Dermatologist", com.example.mediplus20.R.drawable.wow),
    Doctor("Dr. Ahmed Khan", "Neurologist", com.example.mediplus20.R.drawable.wow1),
    Doctor("Dr. Susan Lee", "Pediatrician", com.example.mediplus20.R.drawable.wow),
    Doctor("Dr. Raj Patel", "Orthopedic Surgeon", com.example.mediplus20.R.drawable.wow1),
    Doctor("Dr. Maria Gonzalez", "Psychiatrist", com.example.mediplus20.R.drawable.wow),
    Doctor("Dr. Kevin Zhang", "Oncologist", com.example.mediplus20.R.drawable.wow1),
    Doctor("Dr. Angela White", "Gynecologist", com.example.mediplus20.R.drawable.wow),
    Doctor("Dr. John Okoro", "General Practitioner",com.example.mediplus20.R.drawable.wow1),
    Doctor("Dr. Chloe Dubois", "Endocrinologist", com.example.mediplus20.R.drawable.wow)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorScreen(navController: NavController) {
    var selectedDoctor by remember { mutableStateOf<Doctor?>(null) }
    var message by remember { mutableStateOf(TextFieldValue("")) }
    val chatMessages = remember { mutableStateListOf<Pair<Boolean, String>>() }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(selectedDoctor?.name ?: "Select a Doctor")
            })
        }
    ) { padding ->
        if (selectedDoctor == null) {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dummyDoctors) { doctor ->
                    DoctorProfileItem(doctor) {
                        selectedDoctor = doctor
                        chatMessages.clear()
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    reverseLayout = true
                ) {
                    items(chatMessages.reversed()) { (isUser, msg) ->
                        ChatBubble(message = msg, isUser = isUser)
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    OutlinedTextField(
                        value = message,
                        onValueChange = { message = it },
                        placeholder = { Text("Type a message") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (message.text.isNotBlank()) {
                                chatMessages.add(true to message.text)
                                chatMessages.add(false to "Thank you for your message. I’ll respond shortly.")
                                message = TextFieldValue("")
                            }
                        }
                    ) {
                        Text("Send")
                    }
                }
            }
        }
    }
}

@Composable
fun DoctorProfileItem(doctor: Doctor, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = doctor.imageRes),
                contentDescription = doctor.name,
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 12.dp)
            )
            Column {
                Text(text = doctor.name, style = MaterialTheme.typography.titleMedium)
                Text(text = doctor.specialty, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}

@Composable
fun ChatBubble(message: String, isUser: Boolean) {
    val bubbleColor = if (isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
    val alignment = if (isUser) Alignment.End else Alignment.Start
    val textColor = if (isUser) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = alignment as Alignment
    ) {
        Surface(
            color = bubbleColor,
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 2.dp,
            modifier = Modifier.padding(4.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(12.dp),
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DoctorScreenPreview() {
    MediPlus20Theme(darkTheme = true) {
        DoctorScreen(navController = rememberNavController())
    }
}

