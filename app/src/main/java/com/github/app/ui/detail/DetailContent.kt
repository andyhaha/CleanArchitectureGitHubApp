package com.github.app.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.app.ui.component.LoadingContent

@Composable
fun DetailContent(
    username: String?,
    innerPadding: PaddingValues,
    detailViewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(username) {
        getUserWithRepositories(detailViewModel, username)
    }

    val uiState by detailViewModel.combinedUiState.collectAsState()
    if (uiState is DetailUiState.Error) {
        ErrorContent(onRetry = {
            getUserWithRepositories(detailViewModel, username)
        })
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (uiState) {
            is DetailUiState.Loading -> {
                item {
                    LoadingContent()
                }
            }

            is DetailUiState.Success -> {
                val userWithRepositories = (uiState as DetailUiState.Success).userWithRepositories
                item {
                    UserHeader(user = userWithRepositories.user)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                items(userWithRepositories.repositories) { repo ->
                    val context = LocalContext.current
                    RepositoryCard(repo) {
                        if (repo.githubUrl.isNullOrBlank()) {
                            return@RepositoryCard
                        }
                        if (repo.isPrivate == true) {
                            return@RepositoryCard
                        }
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.githubUrl))
                        context.startActivity(intent)
                    }
                }
            }

            else -> {}
        }
    }
}

private fun getUserWithRepositories(
    detailViewModel: DetailViewModel,
    username: String?
) {
    username?.let {
        detailViewModel.getUserWithRepositories(it)
    }
}