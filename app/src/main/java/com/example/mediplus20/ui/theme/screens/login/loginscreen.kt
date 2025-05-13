package com.example.mediplus20.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mediplus20.navigation.ROUTE_HOME
import com.example.mediplus20.navigation.ROUTE_LOGIN
import com.example.mediplus20.navigation.ROUTE_SIGNUP
import com.example.mediplus20.ui.theme.MediPlus20Theme
import com.example.mediplus20.utils.SessionManager

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = com.example.mediplus20.R.drawable.getty),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Surface(
            color = Color.Black.copy(alpha = 0.5f),
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        if (email.isNotBlank() && password.isNotBlank()) {
                            SessionManager.login()
                            navController.navigate(ROUTE_HOME) {
                                popUpTo(ROUTE_LOGIN) { inclusive = true }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }

                TextButton(onClick = { navController.navigate(ROUTE_SIGNUP) }) {
                    Text("Don't have an account? Sign Up")
                }
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPreview() {
    MediPlus20Theme(darkTheme = true) {
        LoginScreen(navController = rememberNavController())
    }
}
