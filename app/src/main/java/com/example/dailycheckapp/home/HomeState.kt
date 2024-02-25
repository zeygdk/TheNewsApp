package com.example.dailycheckapp.home

data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
)