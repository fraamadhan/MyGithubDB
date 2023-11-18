package com.example.mygithubdb.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile: Screen("profile")
    object DetailUser: Screen("home/{username}") {
        fun createRoute(username: String) = "home/$username"
    }

}