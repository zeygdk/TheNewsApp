package com.example.dailycheckapp.domain.usecases

import com.example.dailycheckapp.domain.LocalUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppEntry @Inject constructor(
    private val localUser: LocalUser
) {
    operator fun invoke(): Flow<Boolean> {
        return localUser.readAppEntry()
    }
}
