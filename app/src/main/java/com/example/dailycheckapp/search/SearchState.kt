package com.example.dailycheckapp.search

import androidx.paging.PagingData
import com.example.dailycheckapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)