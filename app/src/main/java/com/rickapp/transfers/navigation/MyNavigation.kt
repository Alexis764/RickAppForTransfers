package com.rickapp.transfers.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rickapp.transfers.feature.home.HomeScreen
import com.rickapp.transfers.feature.splash.SplashScreen

@Composable
fun MyNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen,
        modifier = modifier
    ) {
        composable<Routes.SplashScreen> {
            SplashScreen(navController)
        }
        composable<Routes.HomeScreen> {
            HomeScreen()
        }
    }
}