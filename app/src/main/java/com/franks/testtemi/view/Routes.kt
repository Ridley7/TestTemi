package com.franks.testtemi.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Routes(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home-navigation"
    ){
        composable("home-navigation"){
            HomeScreenNavigation(navController)
        }

        composable(
            route = "details/{itemName}",
            arguments = listOf(navArgument("itemName") { type = NavType.StringType})
        ){ backStackEntry ->
            val itemName = backStackEntry.arguments?.getString("itemName")
            DetailScreen(navController, itemName)
        }
    }
}

