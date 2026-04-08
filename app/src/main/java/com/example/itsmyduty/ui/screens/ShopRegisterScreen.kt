package com.example.itsmyduty.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.itsmyduty.viewmodel.ShopViewModel

@Composable
fun ShopRegisterScreen(viewModel: ShopViewModel = viewModel()) {

    var shopName by remember { mutableStateOf("") }
    var ownerName by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Register Repair Shop",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = shopName,
            onValueChange = { shopName = it },
            label = { Text("Shop Name") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = ownerName,
            onValueChange = { ownerName = it },
            label = { Text("Owner Name") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.registerShop(
                    shopName,
                    ownerName,
                    address
                )
            }
        ) {

            Text("Submit for Verification")

        }

    }
}