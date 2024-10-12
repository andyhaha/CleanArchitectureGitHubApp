package com.github.app.ui

//import com.github.app.ui.home.HomeSearchBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.github.app.ui.home.HomeScreen
import com.github.app.ui.home.HomeViewModel
import com.github.app.ui.theme.GitHubAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubAppTheme {
                HomeScreen()
            }
        }
    }
}