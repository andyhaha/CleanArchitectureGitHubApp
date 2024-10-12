package com.github.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.github.app.ui.detail.DetailScreen
import com.github.app.ui.home.HomeScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeScreen(navController = navController)
        }

        composable<Screen.Detail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Screen.Detail>()
            DetailScreen(
                navController = navController,
                username = detail.username
            )
        }
    }
}
