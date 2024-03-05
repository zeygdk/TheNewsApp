package com.example.dailycheckapp.onboarding.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dailycheckapp.onboarding.screen.WelcomeScreen

import com.example.dailycheckapp.onboarding.viewmodel.WelcomeViewModel
import com.example.dailycheckapp.search.SearchScreen
import com.example.dailycheckapp.search.SearchViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun NavGraph(startDestination: String, navController: NavHostController) {
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Screen.AppStartNavigation.route,
            startDestination = Screen.WelcomeScreen.route
        ) {
            composable(route = Screen.WelcomeScreen.route) {
                val viewModel: WelcomeViewModel = hiltViewModel()
                WelcomeScreen(navController = navController, onEvent = viewModel::onEvent)
            }
        }
        navigation(
            route = Screen.NewsNavigation.route,
            startDestination = Screen.NewsNavigatorScreen.route
        ) {
            composable(route = Screen.NewsNavigatorScreen.route){

            }
        }

        composable(route = Screen.HomeScreen.route) {
        }
    }
}
