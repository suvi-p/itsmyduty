package com.example.itsmyduty.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.itsmyduty.ui.screens.*

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash") {

        composable("splash") {
            SplashScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("register") {
            RegisterScreen(navController)
        }

        // ✅ SERVICE REQUEST (1 PARAM)
        composable("serviceRequest/{serviceName}") { backStackEntry ->
            val serviceName = backStackEntry.arguments?.getString("serviceName") ?: ""
            ServiceRequestScreen(
                serviceName = serviceName,
                phoneNumber = "9876543210", // Default phone
                navController = navController
            )
        }

        // ✅ SERVICE REQUEST (2 PARAMS)
        composable("serviceRequest/{serviceName}/{phoneNumber}") { backStackEntry ->
            val serviceName = backStackEntry.arguments?.getString("serviceName") ?: ""
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            ServiceRequestScreen(
                serviceName = serviceName,
                phoneNumber = phoneNumber,
                navController = navController
            )
        }

        // ✅ ORDER TRACKING (1 PARAM)
        composable("orderTracking/{serviceName}") { backStackEntry ->
            val serviceName = backStackEntry.arguments?.getString("serviceName") ?: ""
            OrderTrackingScreen(
                serviceName = serviceName,
                phoneNumber = "9876543210", // Default phone
                navController = navController
            )
        }

        // ✅ ORDER TRACKING (2 PARAMS)
        composable("orderTracking/{serviceName}/{phoneNumber}") { backStackEntry ->
            val serviceName = backStackEntry.arguments?.getString("serviceName") ?: ""
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OrderTrackingScreen(
                serviceName = serviceName,
                phoneNumber = phoneNumber,
                navController = navController
            )
        }

        composable("home") {
            HomeScreen(navController)
        }
    }
}
