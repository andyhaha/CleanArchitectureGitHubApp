package com.andy.github.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.tooling.preview.Preview
import com.andy.github.home.domain.model.SearchItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSearchBar(
    searchUiState: SearchUiState = SearchUiState.Loading,
    onSearch: (SearchItem) -> Unit = {},
    delete: (SearchItem) -> Unit = {}
) {
    var text by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    Box(
        Modifier
            .fillMaxWidth()
            .semantics { isTraversalGroup = true }) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = text,
                    onQueryChange = { text = it },
                    onSearch = {
                        expanded = false
                        onSearch(SearchItem(content = it))
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text("Search users") },
                    leadingIcon = {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "search icon",
                            modifier = Modifier.clickable {
                                expanded = false
                                onSearch(SearchItem(content = text))
                            }
                        )
                    },
                    trailingIcon = {
                        if (expanded) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "close icon",
                                modifier = Modifier.clickable {
                                    expanded = false
                                    text = ""
                                }
                            )
                        }
                    }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            SearchHistory(
                onItemClick = {
                    text = it.content
                    expanded = false
                    onSearch(it)
                },
                delete = delete,
                state = searchUiState
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreenBar() {
    HomeSearchBar()
}