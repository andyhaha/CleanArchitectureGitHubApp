package com.andy.github.details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CallSplit
import androidx.compose.material.icons.filled.Star
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
import com.andy.github.details.domain.model.Repository

@Composable
fun RepositoryCard(repo: Repository, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(8.dp),
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(8.dp),
            )
            .clickable { onClick() }
            .padding(16.dp),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = repo.formattedName(),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.width(8.dp))
                RepoTypeBadge(repo.formattedRepoType())
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                text = repo.formattedDescription(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                LanguageBadge(repo.language ?: "Unknown")
                MetricChip(
                    icon = Icons.Default.Star,
                    text = repo.formattedStarsCount(),
                )
                MetricChip(
                    icon = Icons.AutoMirrored.Filled.CallSplit,
                    text = repo.formattedForksCount(),
                )
            }
        }
    }
}

@Composable
private fun RepoTypeBadge(type: String) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(50),
            )
            .padding(horizontal = 8.dp, vertical = 2.dp),
    ) {
        Text(
            text = type,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Composable
private fun MetricChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
        )
    }
}

@Composable
fun LanguageBadge(language: String) {
    val languageColor = getLanguageColor(language)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(languageColor, shape = CircleShape),
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = language,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.labelMedium,
        )
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
        "Rust" -> Color(0xFFDEA584)
        "Dart" -> Color(0xFF00B4AB)
        else -> Color.Gray
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRepoCard() {
    RepositoryCard(
        repo = Repository(
            name = "Spoon-Knife",
            language = "Kotlin",
            starsCount = 12600,
            description = "This repo is for demonstration purposes only.",
            githubUrl = "https://github.com/octocat/Spoon-Knife",
            forksCount = 14600,
            isPrivate = false,
        )
    )
}
