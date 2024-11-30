package com.andy.github.home.ui

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.andy.github.home.R
import com.andy.github.home.domain.model.SimpleUser

@Composable
fun UserItem(
    simpleUser: SimpleUser,
    onSearchListItemClick: (SimpleUser) -> Unit = {}
) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSearchListItemClick(simpleUser)
            },
        headlineContent = { Text(text = simpleUser.name) },
        leadingContent = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(simpleUser.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Net image",
                placeholder = painterResource(
                    id = R.drawable.baseline_person_50
                ),
                error = painterResource(
                    R.drawable.baseline_person_off_50
                ),
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }
    )
    HorizontalDivider(thickness = 0.5.dp)
}