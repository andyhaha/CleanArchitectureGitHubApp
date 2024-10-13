package com.andy.github.details.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andy.github.details.domain.model.User
import com.andy.github.details.domain.model.formatFollowers
import com.andy.github.details.domain.model.formatFollowing

@Composable
fun UserConnections(user: User) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.Group,
            contentDescription = "Favorite"
        )
        Spacer(modifier = Modifier.width(3.dp))
        ColorfulText(user.formatFollowers(), "followers")
        Text(
            modifier = Modifier
                .width(12.dp)
                .align(Alignment.CenterVertically),
            text = "·",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
        ColorfulText(user.formatFollowing(), "following")
    }
}

@Composable
fun ColorfulText(
    startText: String = "Hello",
    endText: String = "World"
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontStyle = MaterialTheme.typography.titleSmall.fontStyle,
                fontWeight = FontWeight.SemiBold
            )
        ) {
            append("$startText ")
        }
        withStyle(style = SpanStyle(color = Color.Black.copy(0.5f))) {
            append(endText)
        }
    }
    Text(text = annotatedText)
}

@Preview(showBackground = true)
@Composable
fun PreviewUserConnections() {
    val user = User(
        name = "JohnDoe",
        avatarUrl = "https://example.com/avatar.jpg",
        fullName = "John Doe",
        followers = 9000,
        following = 19
    )
    UserConnections(user)
}

@Preview(showBackground = true)
@Composable
fun PreviewColorfulText() {
    ColorfulText()
}