package com.example.dailycheckapp.onboarding.navigation.newsnavigator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.dailycheckapp.R
import com.example.dailycheckapp.bookmark.BookmarkViewModel
import com.example.dailycheckapp.details.DetailsViewModel
import com.example.dailycheckapp.domain.model.Article
import com.example.dailycheckapp.home.HomeViewModel
import com.example.dailycheckapp.onboarding.navigation.Screen
import com.example.dailycheckapp.search.SearchViewModel
import com.example.dailycheckapp.home.HomeScreen
import com.example.dailycheckapp.search.SearchScreen
import com.example.dailycheckapp.details.DetailsScreen
import com.example.dailycheckapp.bookmark.BookmarkScreen



@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backStackState?.destination?.route) {
        Screen.HomeScreen.route -> 0
        Screen.SearchScreen.route -> 1
        Screen.BookmarkScreen.route -> 2
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Screen.HomeScreen.route ||
                backStackState?.destination?.route == Screen.SearchScreen.route ||
                backStackState?.destination?.route == Screen.BookmarkScreen.route
    }


    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Screen.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Screen.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Screen.BookmarkScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Screen.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = {
                        navigateToTab(
                            navController = navController,
                            route = Screen.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    },
                    event = viewModel:: onEvent,
                    state = viewModel.state.value
                )
            }
            composable(route = Screen.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state
                OnBackClickStateSaver(navController = navController)
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navController = navController, // Add this parameter
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Screen.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() },
                            sideEffect = viewModel.sideEffect
                        )
                    }

            }
            composable(route = Screen.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                BookmarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Screen.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            popUpTo(screenRoute) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Screen.DetailsScreen.route
    )
}