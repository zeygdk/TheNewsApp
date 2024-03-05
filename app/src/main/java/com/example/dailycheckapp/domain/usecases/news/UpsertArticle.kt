package com.example.dailycheckapp.domain.usecases.news

import com.example.dailycheckapp.data.local.NewsDao
import com.example.dailycheckapp.domain.model.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article = article)
    }

}