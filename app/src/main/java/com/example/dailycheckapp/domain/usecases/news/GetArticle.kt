package com.example.dailycheckapp.domain.usecases.news

import com.example.dailycheckapp.data.local.NewsDao
import com.example.dailycheckapp.domain.model.Article

class GetArticle (
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article?{
        return newsDao.getArticle(url = url)
    }

}