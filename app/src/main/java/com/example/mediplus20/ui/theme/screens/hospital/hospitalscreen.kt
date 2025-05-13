package com.example.mediplus20.ui.theme.screens.hospital

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediplus20.navigation.ROUTE_DOCTOR
import com.example.mediplus20.ui.theme.MediPlus20Theme

data class Hospital(
    val name: String,
    val location: String,
    val type: String = "Referral" // or "Private", "Mission", etc.
)

val topKenyanHospitals = listOf(
    Hospital("Kenyatta National Hospital", "Nairobi", "Referral"),
    Hospital("Aga Khan University Hospital", "Nairobi", "Private"),
    Hospital("The Nairobi Hospital", "Nairobi", "Private"),
    Hospital("Moi Teaching and Referral Hospital", "Eldoret", "Referral"),
    Hospital("MP Shah Hospital", "Nairobi", "Private"),
    Hospital("Karen Hospital", "Nairobi", "Private"),
    Hospital("Coast General Teaching & Referral Hospital", "Mombasa", "Referral"),
    Hospital("Tenwek Mission Hospital", "Bomet", "Mission"),
    Hospital("Texas Cancer Centre", "Nairobi", "Private"),
    Hospital("Nairobi Women’s Hospital", "Nairobi", "Private")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nearby Hospitals") },
                actions = {
                    Button(
                        onClick = { navController.navigate(ROUTE_DOCTOR) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Doctors")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(topKenyanHospitals) { hospital ->
                HospitalCard(hospital)
            }
        }
    }
}

@Composable
fun HospitalCard(hospital: Hospital) {
    Card(
        elevation = CardDefaults.cardElevation(6.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click to hospital detail */ }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(hospital.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text(hospital.location, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
            HospitalTypeTag(hospital.type)
        }
    }
}

@Composable
fun HospitalTypeTag(type: String) {
    val backgroundColor = when (type) {
        "Referral" -> Color(0xFFE3F2FD)
        "Private" -> Color(0xFFFFF3E0)
        "Mission" -> Color(0xFFE8F5E9)
        else -> Color.LightGray
    }
    val textColor = when (type) {
        "Referral" -> Color(0xFF1976D2)
        "Private" -> Color(0xFFEF6C00)
        "Mission" -> Color(0xFF2E7D32)
        else -> Color.DarkGray
    }

    Box(
        modifier = Modifier
            .background(color = backgroundColor, shape = MaterialTheme.shapes.small)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = type,
            color = textColor,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HospitalScreenPreview() {
    MediPlus20Theme(darkTheme = true) {
        HospitalsScreen(navController = rememberNavController())
    }
}
