package com.example.dailycheckapp.domain.usecases

import com.example.dailycheckapp.domain.LocalUser

class SaveAppEntry(
private val localUser: LocalUser
) {

    suspend operator fun invoke(){
        localUser.saveAppEntry()
    }

}