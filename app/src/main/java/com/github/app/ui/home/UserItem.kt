package com.github.app.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.github.app.domain.model.SimpleUser
import com.github.app.navigation.Screen

@Composable
fun UserItem(navController: NavController, simpleUser: SimpleUser) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.Detail(simpleUser.name))
            },
        headlineContent = { Text(text = simpleUser.name) },
        leadingContent = {
            AsyncImage(
                model = simpleUser.avatarUrl,
                contentDescription = "Net image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
    )
    HorizontalDivider(thickness = 0.5.dp)
}