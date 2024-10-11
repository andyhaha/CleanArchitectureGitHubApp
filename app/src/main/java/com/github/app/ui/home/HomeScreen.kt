package com.github.app.ui.home

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            HomeSearchBar {
                searchText = it
            }
        }
    ) { innerPadding ->
        HomeContent(innerPadding, searchText)
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}