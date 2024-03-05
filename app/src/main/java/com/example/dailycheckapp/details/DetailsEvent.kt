package com.example.dailycheckapp.details

sealed class DetailsEvent {

    data object SaveArticle : DetailsEvent()

}