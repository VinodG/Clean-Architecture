package com.clean.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.clean.home.HomeScreen

fun NavController.navigateToHome() {
    navigate(HomeRoute)
}

fun NavGraphBuilder.homeScreen() {
    composable<HomeRoute> {
        HomeScreen()
    }
}