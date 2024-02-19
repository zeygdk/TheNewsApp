package com.example.dailycheckapp.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailycheckapp.domain.usecases.AppEntryUseCases
import com.example.dailycheckapp.onboarding.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
) : ViewModel() {

    private val _splashCondition = MutableStateFlow(true)
    val splashCondition: StateFlow<Boolean> = _splashCondition

    private val _startDestination = MutableStateFlow(Screen.AppStartNavigation.route)
    val startDestination: StateFlow<String> = _startDestination

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect { shouldStartFromHomeScreen: Boolean ->
                if (shouldStartFromHomeScreen) {
                    _startDestination.value = Screen.NewsNavigation.route
                } else {
                    _startDestination.value = Screen.AppStartNavigation.route
                }
                delay(200)
                _splashCondition.value = false
            }
        }
    }
}
