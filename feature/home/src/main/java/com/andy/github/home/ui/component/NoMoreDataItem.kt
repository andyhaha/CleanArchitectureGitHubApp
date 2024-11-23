package com.andy.github.home.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoMoreDataItem() {
    Box(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "No more data")
    }
}