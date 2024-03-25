package com.example.dailycheckapp.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.dailycheckapp.common.ArticlesList
import com.example.dailycheckapp.common.ArticlesListPaged
import com.example.dailycheckapp.common.SearchBar
import com.example.dailycheckapp.domain.model.Article

@Composable
fun SearchScreen(
    state: State<SearchState>,
    event: (SearchEvent) -> Unit,
    navController: NavHostController,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            .statusBarsPadding()
    ) {
        SearchBar(
            text = state.value.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        state.value.articles?.let {  // Access the value property
            val articles = it.collectAsLazyPagingItems()
            ArticlesListPaged(
                articles = articles,
                onClick = navigateToDetails
            )
        }
    }
}
