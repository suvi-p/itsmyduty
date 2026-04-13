package com.example.itsmyduty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun ServiceRequestScreen(
    serviceName: String,
    phoneNumber: String,
    navController: NavController
) {

    val decodedService = URLDecoder.decode(serviceName, "UTF-8")

    var problemText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val primaryBlue = Color(0xFF1565C0)
    val lightBlue = Color(0xFFE3F2FD)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {

        //  SELECTED SERVICE INFO - Moved to top
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = lightBlue.copy(alpha = 0.4f)),
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "SELECTED SERVICE",
                    fontSize = 16.sp,
                    color = primaryBlue,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = decodedService,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        //  HEADER - Matched font size with problem description
        Text(
            text = "How can we help you today?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF212121),
            letterSpacing = 0.25.sp
        )

        Spacer(modifier = Modifier.height(8.dp)) // Kept near as requested previously

        //  PROBLEM DESCRIPTION BOX
        Text(
            text = "Describe your problem",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color(0xFF212121)
        )

        Spacer(modifier = Modifier.height(14.dp))

        OutlinedTextField(
            value = problemText,
            onValueChange = { problemText = it },
            placeholder = { Text("What seems to be the issue?", fontSize = 15.sp, color = Color.LightGray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            shape = RoundedCornerShape(16.dp),
            textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryBlue,
                unfocusedBorderColor = Color(0xFFE0E0E0),
                focusedContainerColor = Color(0xFFFAFAFA),
                unfocusedContainerColor = Color(0xFFFAFAFA)
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        //  ACTION BUTTONS - Unified Blue style with good font size
        Button(
            onClick = {
                if (problemText.isNotBlank()) {
                    showDialog = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = primaryBlue),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Submit Request", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val encodedService = URLEncoder.encode(decodedService, "UTF-8")
                val encodedPhone = URLEncoder.encode(phoneNumber, "UTF-8")
                navController.navigate("orderTracking/$encodedService/$encodedPhone")
            },
            colors = ButtonDefaults.buttonColors(containerColor = primaryBlue),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Icon(Icons.Default.LocationOn, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Track Order", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(containerColor = primaryBlue),
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Back", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        problemText = ""
                    }
                ) {
                    Text("OK", color = primaryBlue, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            },
            title = { Text("Request Submitted", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            text = { Text("We have received your request for $decodedService. A technician will contact you shortly.", fontSize = 16.sp) },
            shape = RoundedCornerShape(24.dp),
            containerColor = Color.White
        )
    }
}
