package com.example.mygithubdb

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mygithubdb.ui.navigation.NavigationItem
import com.example.mygithubdb.ui.navigation.Screen
import com.example.mygithubdb.ui.screen.detail.DetailScreen
import com.example.mygithubdb.ui.screen.home.HomeScreen
import com.example.mygithubdb.ui.screen.profile.ProfileScreen
import com.example.mygithubdb.ui.theme.MyGithubDBTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GithubUserApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Log.d("CURRENT DESTINATION", currentRoute.toString())

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailUser.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    username = "Fakhri",
                    navigateToDetail = {username ->
                        navController.navigate(Screen.DetailUser.createRoute(username))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailUser.route,
                arguments = listOf(navArgument("username") {type = NavType.StringType}),
            ) {
                val username = it.arguments?.getString("username") ?: ""
                DetailScreen(username = username, navigateBack = { navController.navigateUp() })
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItem = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile,
            ),
        )
        navigationItem.map {item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                modifier = modifier.testTag("bottomBarItem")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GithubUserPreview() {
    MyGithubDBTheme {
        GithubUserApp()
    }
}