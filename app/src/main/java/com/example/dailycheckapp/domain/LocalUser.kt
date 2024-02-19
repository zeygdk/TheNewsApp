package com.example.dailycheckapp.domain

import kotlinx.coroutines.flow.Flow

interface LocalUser {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>

}