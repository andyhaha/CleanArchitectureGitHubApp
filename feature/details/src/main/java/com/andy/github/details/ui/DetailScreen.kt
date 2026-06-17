package com.andy.github.details.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, username: String?) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            DetailAppBar(navController)
        }
    ) { innerPadding ->
        DetailContent(username, innerPadding)
    }
}
