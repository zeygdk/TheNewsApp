package com.example.dailycheckapp.onboarding.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object WelcomeScreen : Screen(route = "welcomeScreen")

    object HomeScreen : Screen(route = "homeScreen")

    object SearchScreen : Screen(route = "searchScreen")

    object BookmarkScreen : Screen(route = "bookMarkScreen")

    object DetailsScreen : Screen(route = "detailsScreen")

    object AppStartNavigation : Screen(route = "appStartNavigation")

    object NewsNavigation : Screen(route = "newsNavigation")

    object NewsNavigatorScreen : Screen(route = "newsNavigator")
}

