package com.example.itsmyduty.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.itsmyduty.viewmodel.HomeViewModel
import androidx.navigation.NavController
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.itsmyduty.R
import androidx.compose.ui.Alignment

// 🔍 BLUE SEARCH BAR
@Composable
fun BlueSearchBar(
    searchQuery: String,
    onSearchChange: (String) -> Unit
) {
    TextField(
        value = searchQuery,
        onValueChange = onSearchChange,
        placeholder = {
            Text("Search services...", color = Color.Gray)
        },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Search", tint = Color(0xFF5C6BC0))
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Clear",
                    modifier = Modifier.clickable { onSearchChange("") },
                    tint = Color.Gray
                )
            }
        },
        shape = RoundedCornerShape(50),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFE8EEFF),
            unfocusedContainerColor = Color(0xFFE8EEFF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

// ⭐ RATING BADGE
@Composable
fun RatingBadge(rating: Double) {
    Row(
        modifier = Modifier
            .background(Color(0xFFF1F3F6), RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFB300), modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = rating.toString(), style = MaterialTheme.typography.bodySmall)
    }
}

// 🟢 TAG CHIP (Verified / Top Rated)
@Composable
fun TagChip(text: String, color: Color) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .background(color.copy(alpha = 0.15f), RoundedCornerShape(10.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

// 🏠 HOME SCREEN
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {

    val services = viewModel.services.value
    var searchQuery by remember { mutableStateOf("") }

    val filteredServices = services.filter {
        it.serviceName.contains(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF5F7FA), Color.White)
                )
            )
            .padding(16.dp)
    ) {

        Text(
            text = "Find Your Service 🔧",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        BlueSearchBar(
            searchQuery = searchQuery,
            onSearchChange = { searchQuery = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(filteredServices) { service ->

                val rating = (4..5).random() + listOf(0.0, 0.1, 0.2, 0.3, 0.4, 0.5).random()
                val distance = listOf("0.8 km", "1.2 km", "2.5 km").random()

                Card(
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF0F4FF) // 🔵 light blue background
                    ),
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth()
                ) {

                    Column {

                        val imageRes = when {
                            service.serviceName.contains("Laptop", true) -> R.drawable.laptop
                            service.serviceName.contains("Mobile", true) -> R.drawable.mobile
                            service.serviceName.contains("Printer", true) -> R.drawable.printer
                            else -> R.drawable.laptop
                        }

                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )

                        Column(modifier = Modifier.padding(16.dp)) {

                            // 🔥 TAGS + RATING
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row {
                                    TagChip("VERIFIED", Color(0xFF2E7D32))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    TagChip("TOP RATED", Color(0xFF1565C0))
                                }
                                RatingBadge(rating)
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = service.shopName,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(
                                text = service.serviceName,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(6.dp))



                            Divider(modifier = Modifier.padding(vertical = 10.dp))

                            // 💰 PRICE + DISTANCE
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text("STARTS FROM", style = MaterialTheme.typography.labelSmall)
                                    Text(
                                        text = "₹${service.price}",
                                        color = Color(0xFF2962FF),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }

                                Column(horizontalAlignment = Alignment.End) {
                                    Text("DISTANCE", style = MaterialTheme.typography.labelSmall)
                                    Text(distance)
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = {
                                    val encodedService = URLEncoder.encode(
                                        service.serviceName,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    navController.navigate("serviceRequest/$encodedService")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                shape = RoundedCornerShape(50),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF2962FF),   // 🔵 blue
                                    contentColor = Color.White           // text color
                                )
                            ) {
                                Text("Request Service")
                            }
                        }
                    }
                }
            }
        }
    }
}