package com.andy.github.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.andy.github.home.domain.model.SimpleUser
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeContent_displaysUserItemsCorrectly() = runTest {
        // Fake data
        // avatarUrl must be null otherwise the test will fail
        // because real avatarUrl is a URL will cause a network request
        val users = listOf(
            SimpleUser(name = "User1", avatarUrl = ""),
            SimpleUser(name = "User2", avatarUrl = ""),
            SimpleUser(name = "User3", avatarUrl = ""),
        )

        // Create a fake PagingData
        val fakePagingData = flowOf(PagingData.from(users))

        composeTestRule.setContent {
            val lazyPagingItems = fakePagingData.collectAsLazyPagingItems()
            HomeContent(
                snackbarHostState = SnackbarHostState(),
                innerPadding = PaddingValues(),
                items = lazyPagingItems,
                onSearchListItemClick = {}
            )
        }

        // Verify that all user items are displayed
        users.forEach { user ->
            composeTestRule.onNodeWithText(user.name).assertIsDisplayed()
        }
    }

    @Test
    fun homeContent_displaysNoUsersWhenEmpty() = runTest {
        // Create an empty PagingData
        val fakePagingData = flowOf(PagingData.empty<SimpleUser>())

        composeTestRule.setContent {
            val lazyPagingItems = fakePagingData.collectAsLazyPagingItems()
            HomeContent(
                snackbarHostState = SnackbarHostState(),
                innerPadding = PaddingValues(),
                items = lazyPagingItems,
                onSearchListItemClick = {}
            )
        }

        // Verify "No users" UI is displayed
        composeTestRule.onNodeWithText("No users").assertIsDisplayed()
    }
}