package com.andy.github.details.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailContent(
    username: String?,
    innerPadding: PaddingValues,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(username) {
        username?.let { detailViewModel.load(it) }
    }

    val uiState by detailViewModel.combinedUiState.collectAsState()
    (uiState as? DetailUiState.Error)?.let { errorState ->
        ErrorContent(
            innerPadding = innerPadding,
            message = errorState.message,
            onRetry = detailViewModel::retry,
        )
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        when (uiState) {
            is DetailUiState.Loading -> {
                item { LoadingContent() }
            }

            is DetailUiState.Success -> {
                val userWithRepositories = (uiState as DetailUiState.Success).userWithRepositories
                item {
                    UserHeader(user = userWithRepositories.user)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Popular repositories",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    )
                }
                items(userWithRepositories.repositories) { repo ->
                    val context = LocalContext.current
                    RepositoryCard(repo) {
                        if (repo.githubUrl.isNullOrBlank()) return@RepositoryCard
                        if (repo.isPrivate == true) return@RepositoryCard
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.githubUrl))
                        context.startActivity(intent)
                    }
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
            }

            else -> {}
        }
    }
}
