package com.example.dailycheckapp.data.remote.dto

import com.example.dailycheckapp.domain.model.Article

data class NewResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)