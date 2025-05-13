package com.example.mediplus20.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediplus20.navigation.*
import com.example.mediplus20.utils.SessionManager
import com.example.mediplus20.ui.theme.MediPlus20Theme
import com.example.mediplus20.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Welcome to MediPlus", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                },
                actions = {
                    IconButton(onClick = {
                        SessionManager.logout()
                        navController.navigate(ROUTE_LOGIN) {
                            popUpTo(ROUTE_HOME) { inclusive = true }
                        }
                    }) {
                        Icon(painter = painterResource(id = com.example.mediplus20.R.drawable.l ), contentDescription = "Logout")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FeatureCard("Book Appointment", com.example.mediplus20.R.drawable.a) {
                navController.navigate(ROUTE_APPOINTMENT)
            }
            FeatureCard("Nearby Hospitals", com.example.mediplus20.R.drawable.h) {
                navController.navigate(ROUTE_HOSPITALS)
            }
            FeatureCard("Chat with Doctor", com.example.mediplus20.R.drawable.d) {
                navController.navigate(ROUTE_DOCTOR)
            }
            FeatureCard("Reminders", com.example.mediplus20.R.drawable.r) {
                navController.navigate(ROUTE_REMINDER)
            }
        }
    }
}

@Composable
fun FeatureCard(title: String, imageResId: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(6.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = title,
                modifier = Modifier.size(48.dp) // Adjust size of images here
            )
            Text(title, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    MediPlus20Theme(darkTheme = true) {
        HomeScreen(navController = rememberNavController())
    }
}
