package com.example.dailycheckapp.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dailycheckapp.common.ArticlesList
import com.example.dailycheckapp.domain.model.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Bookmark") }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                ArticlesList(
                    articles = state.articles,
                    onClick = navigateToDetails
                )
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewBookmarkScreen() {
    val mockState = BookmarkState(articles = listOf())
    val mockNavigateToDetails: (Article) -> Unit = {}

    BookmarkScreen(
        state = mockState,
        navigateToDetails = mockNavigateToDetails
    )
}
