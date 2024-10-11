package com.github.app.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeContent(innerPadding: PaddingValues, searchText: String = "") {
    Log.d("HomeContent", "searchText = $searchText")
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(
                PaddingValues(horizontal = 10.dp)
            ),
            text = "This is an example of a scaffold. I am the content!"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
    HomeContent(PaddingValues())
}

