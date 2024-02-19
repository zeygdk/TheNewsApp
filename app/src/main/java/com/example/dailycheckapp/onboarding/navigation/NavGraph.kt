package com.example.dailycheckapp.onboarding.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.dailycheckapp.onboarding.screen.HomeScreen
import com.example.dailycheckapp.onboarding.screen.WelcomeScreen
import com.example.dailycheckapp.onboarding.viewmodel.WelcomeViewModel

import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun NavGraph(startDestination: String, navController: NavHostController) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Screen.AppStartNavigation.route,
            startDestination = Screen.OnBoardingScreen.route
        ) {
            composable(route = Screen.OnBoardingScreen.route) {
                val viewModel: WelcomeViewModel = hiltViewModel()
                WelcomeScreen(navController = navController, onEvent = viewModel::onEvent)
            }
        }

        navigation(
            route = Screen.NewsNavigation.route,
            startDestination = Screen.Home.route
        ) {
            composable(route = Screen.Home.route) {
                // Your Home screen content
            }
            composable(route = Screen.SearchScreen.route) {
                // Your Search screen content
            }
            composable(route = Screen.BookmarkScreen.route) {
                // Your Bookmark screen content
            }
            composable(route = Screen.DetailsScreen.route) {
                // Your Details screen content
            }
        }

        composable(route = Screen.Welcome.route) {
        }
    }
}
