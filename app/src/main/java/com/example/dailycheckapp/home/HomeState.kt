package com.example.dailycheckapp.home

data class HomeState(
    val newsTicker: String = "",
    val isLoading: Boolean = false,
    val scrollValue: Int = 0,
    val maxScrollingValue: Int = 0
)