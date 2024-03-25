package com.example.dailycheckapp.details

import com.example.dailycheckapp.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()

    data object RemoveSideEffect : DetailsEvent()

}