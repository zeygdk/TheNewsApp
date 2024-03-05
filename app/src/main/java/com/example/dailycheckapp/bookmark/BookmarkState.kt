package com.example.dailycheckapp.bookmark

import com.example.dailycheckapp.domain.model.Article


data class BookmarkState(
    val articles: List<Article> = emptyList()
)