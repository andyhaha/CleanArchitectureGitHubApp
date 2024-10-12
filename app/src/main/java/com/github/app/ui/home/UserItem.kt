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
import com.github.app.domain.model.User
import com.github.app.navigation.Screen

@Composable
fun UserItem(navController: NavController, user: User) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.Detail(user.name))
            },
        headlineContent = { Text(text = user.name) },
        leadingContent = {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = "Net image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
    )
    HorizontalDivider(thickness = 0.5.dp)
}