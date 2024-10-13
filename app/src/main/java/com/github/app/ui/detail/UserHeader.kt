package com.github.app.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.app.domain.model.User
import com.github.app.domain.model.formatFullName
import com.github.app.domain.model.formatName

@Composable
fun UserHeader(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = user.avatarUrl,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Text(
            text = user.formatFullName(),
            modifier = Modifier.padding(top = 8.dp),
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight
        )
        Text(
            text = user.formatName(),
            color = Color.Gray,
            style = MaterialTheme.typography.titleSmall,
        )
        UserConnections(user)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserHeader() {
    val user = User(
        name = "JohnDoe",
        avatarUrl = "https://example.com/avatar.jpg",
        fullName = "John Doe",
        followers = 9000,
        following = 1500
    )
    UserHeader(user)
}
