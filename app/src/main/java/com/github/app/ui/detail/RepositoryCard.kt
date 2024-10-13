package com.github.app.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CallSplit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.app.domain.model.Repository

@Composable
fun RepositoryCard(repo: Repository, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                onClick()
            },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Repo name
                Text(
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = repo.formattedName(),
                    color = Color.Blue,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .background(Color.White, shape = CircleShape)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "Public",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Repo description
            Text(
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = repo.formattedDescription(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Stats: Language, Stars, Forks
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Language
                LanguageBadge("Java")

                // Stars
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = repo.formattedStarsCount(), color = Color.Gray)
                }

                // Forks
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CallSplit, // Fork icon
                        contentDescription = "Fork",
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = repo.formattedForksCount(), color = Color.Gray)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRepoCard() {
    RepositoryCard(
        repo = Repository(
            name = "Spoon-Knife",
            language = "Java",
            starsCount = 12600,
            description = "This repo is for demonstration purposes only.",
            githubUrl = "https://github.com/octocat/Spoon-Knife",
            forksCount = 14600
        )
    )
}

@Composable
fun LanguageBadge(language: String) {
    val languageColor = getLanguageColor(language)

    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(languageColor, shape = CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = language, color = Color.Gray)
    }
}

@Composable
fun getLanguageColor(language: String): Color {
    return when (language) {
        "HTML" -> Color(0xFFE34C26)
        "CSS" -> Color(0xFF563D7C)
        "Ruby" -> Color(0xFF701516)
        "JavaScript" -> Color(0xFFF1E05A)
        "Python" -> Color(0xFF3572A5)
        "Java" -> Color(0xFFB07219)
        "Go" -> Color(0xFF00ADD8)
        "C++" -> Color(0xFFF34B7D)
        "Kotlin" -> Color(0xFFA97BFF)
        "Swift" -> Color(0xFFFFAC45)
        "PHP" -> Color(0xFF4F5D95)
        "TypeScript" -> Color(0xFF2B7489)
        "C#" -> Color(0xFF178600)
        "Shell" -> Color(0xFF89E051)
        else -> Color.Gray // Default color for unlisted languages
    }
}