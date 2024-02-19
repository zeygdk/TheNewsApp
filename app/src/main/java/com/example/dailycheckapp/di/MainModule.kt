package com.example.dailycheckapp.di

import android.app.Application
import android.content.Context
import com.example.dailycheckapp.data.LocalUserImpl
import com.example.dailycheckapp.domain.LocalUser
import com.example.dailycheckapp.domain.usecases.AppEntryUseCases
import com.example.dailycheckapp.domain.usecases.ReadAppEntry
import com.example.dailycheckapp.domain.usecases.SaveAppEntry
import com.example.dailycheckapp.onboarding.data.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideLocalUser(
        application : Application
    ):LocalUser = LocalUserImpl(application)
    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)
    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUser: LocalUser
    ) : AppEntryUseCases = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUser),
        saveAppEntry = SaveAppEntry(localUser)
    )

}