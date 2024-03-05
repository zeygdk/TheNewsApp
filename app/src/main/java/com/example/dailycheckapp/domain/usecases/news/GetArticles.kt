package com.example.dailycheckapp.domain.usecases.news

import com.example.dailycheckapp.data.local.NewsDao
import com.example.dailycheckapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

}