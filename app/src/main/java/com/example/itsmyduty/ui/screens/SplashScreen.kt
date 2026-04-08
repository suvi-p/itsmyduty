package com.example.itsmyduty.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import com.example.itsmyduty.R

@Composable
fun SplashScreen(navController: NavController) {

    //  Animation (logo zoom)
    val scale = remember { Animatable(0.7f) }

    LaunchedEffect(true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(1200, easing = EaseOutBack)
        )

        delay(1500)

        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A0F1C),  // deep navy
                        Color(0xFF121A2A),  // dark blue
                        Color(0xFF1B2A3A)   // soft blue
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //  Logo with animation
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(150.dp)
                    .scale(scale.value)
            )

            Spacer(modifier = Modifier.height(24.dp))

            //  Brand Name
            Text(
                text = "Its My Duty",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD4AF37) // gold tone
            )

            Spacer(modifier = Modifier.height(6.dp))

            //  Tagline
            Text(
                text = "Premium Repair Services",
                fontSize = 14.sp,
                color = Color.LightGray
            )
        }

        // Bottom tagline
        Text(
            text = "Reliable • Professional • Trusted",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp),
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}