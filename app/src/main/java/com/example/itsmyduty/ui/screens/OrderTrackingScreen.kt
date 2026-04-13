package com.example.itsmyduty.ui.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.net.URLDecoder
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri

@Composable
fun OrderTrackingScreen(
    serviceName: String,
    phoneNumber: String,
    navController: NavController
) {

    val decodedService = URLDecoder.decode(serviceName, "UTF-8")
    val decodedPhone = URLDecoder.decode(phoneNumber, "UTF-8")
    val context = LocalContext.current
    var showCancelDialog by remember { mutableStateOf(false) }
    
    val primaryButtonColor = Color(0xFF2962FF) // Consistent blue for all buttons

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFE3F2FD), Color.White) //  blue gradient
                )
            )
            .padding(16.dp)
    ) {

        //  HEADER WITH BLUE BACKGROUND
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = primaryButtonColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    Icons.Default.Build,
                    contentDescription = null,
                    tint = Color.White
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "Track Your Service",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        //  MAIN STATUS CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {

                // Service Info
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Build, contentDescription = null, tint = Color(0xFF1976D2))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Service Requested", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                        Text(decodedService, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Status Info
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF4CAF50))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Current Status", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                        Text(
                            "Technician Assigned",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Time Info
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Info, contentDescription = null, tint = Color(0xFF1976D2))
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text("Expected Completion", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
                        Text("Within 2 hours", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        //  CALL BUTTON
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = "tel:$decodedPhone".toUri()
                }
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryButtonColor,
                contentColor = Color.White
            )
        ) {
            Icon(Icons.Default.Call, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Call Technician", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // CANCEL BUTTON
        Button(
            onClick = { showCancelDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryButtonColor,
                contentColor = Color.White
            )
        ) {
            Icon(Icons.Default.Close, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Cancel Request", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(12.dp))

        //  BACK BUTTON
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = primaryButtonColor,
                contentColor = Color.White
            )
        ) {
            Text("Back", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }

    // CANCEL DIALOG
    if (showCancelDialog) {
        AlertDialog(
            onDismissRequest = { showCancelDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showCancelDialog = false
                    navController.popBackStack()
                }) {
                    Text(
                        "Confirm Cancellation",
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showCancelDialog = false }) {
                    Text(
                        "Keep Request",
                        color = primaryButtonColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            title = { Text("Cancel Service Request?") },
            text = {
                Text("Are you sure you want to cancel this repair? Our technician has already been notified.")
            }
        )
    }
}
