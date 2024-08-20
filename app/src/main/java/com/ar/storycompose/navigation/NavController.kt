package com.ar.storycompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.ar.storycompose.presentation.home.HomeScreen

sealed class Screen(val route: String){
    data object Home: Screen("home")
    data object DashBoard: Screen("dashboard")
}


@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination =  "main"){
        mainGraph(navController)
    }
}

private fun NavGraphBuilder.mainGraph(
    navController: NavHostController
){
    navigation(startDestination = Screen.Home.route, route = "main") {
        composable(Screen.Home.route){
            HomeScreen()
        }
        composable(Screen.DashBoard.route){

        }
    }
}


@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavHostController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}