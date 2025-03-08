package com.sabigny.plmpruebatecnica.navigation

sealed class NavigationRoute(val route: String) {
    object Login: NavigationRoute("login")
    object Search: NavigationRoute("search")
    object Developer: NavigationRoute("developer")

    companion object {
        fun fromRoute(route: String?): NavigationRoute {
            return when(route) {
                Search.route -> Search
                Developer.route -> Developer
                else -> Login
            }
        }
    }
}
