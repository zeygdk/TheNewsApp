package com.example.dailycheckapp.domain.usecases.news



data class NewsUseCase (
    val getNews : GetNews,
    val searchNews : SearchNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val getArticle: GetArticle

)