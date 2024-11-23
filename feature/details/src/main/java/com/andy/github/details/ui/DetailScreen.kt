package com.andy.github.details.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, username: String?) {
    Scaffold(
        modifier = Modifier.padding(bottom = 16.dp),
        topBar = {
            DetailAppBar(navController)
        }
    ) { innerPadding ->
        DetailContent(username, innerPadding)
    }
}