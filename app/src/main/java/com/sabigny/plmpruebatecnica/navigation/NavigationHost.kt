package com.sabigny.plmpruebatecnica.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.sabigny.plmpruebatecnica.auth.ui.LoginScreen
import com.sabigny.plmpruebatecnica.core.ui.components.DrawerLayoutContainer
import com.sabigny.plmpruebatecnica.developer.ui.DeveloperScreen
import com.sabigny.plmpruebatecnica.search.ui.SearchScreen


@Composable
fun NavigationHost(
    navController: NavHostController,
    startDestination: NavigationRoute = NavigationRoute.Login
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    NavHost(
        navController = navController,
        startDestination = startDestination.route
    ) {
        composable(NavigationRoute.Login.route) {
            LoginScreen(
                onLogin = {
                    navController.navigate(NavigationRoute.Search.route) {
                        popUpTo(NavigationRoute.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavigationRoute.Search.route) {
            DrawerLayoutContainer(
                navController = navController,
                currentRoute = currentRoute,
                screenTitle = "Búsqueda"
            ) {
                SearchScreen()
            }
        }

        composable(NavigationRoute.Developer.route) {
            DrawerLayoutContainer(
                navController = navController,
                currentRoute = currentRoute,
                screenTitle = "Desarrollador"
            ) {
                DeveloperScreen()
            }
        }
    }
}