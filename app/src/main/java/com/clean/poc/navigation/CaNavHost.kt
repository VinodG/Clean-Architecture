package com.clean.poc.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.clean.home.navigation.HomeRoute
import com.clean.home.navigation.homeScreen

@Composable
fun CaNavHost(padding: PaddingValues = PaddingValues(0.dp), navHostController: NavHostController) {
    NavHost(
        modifier = Modifier.padding(padding),
        navController = navHostController,
        startDestination = HomeRoute
    ) {
        homeScreen()
    }
}
