package com.example.dailycheckapp.di

import android.app.Application
import android.content.Context
import com.example.dailycheckapp.data.LocalUserImpl
import com.example.dailycheckapp.data.remote.NewApi
import com.example.dailycheckapp.data.repository.NewsRepositoryImpl
import com.example.dailycheckapp.domain.LocalUser
import com.example.dailycheckapp.domain.repository.NewsRepository
import com.example.dailycheckapp.domain.usecases.appentry.AppEntryUseCases
import com.example.dailycheckapp.domain.usecases.appentry.ReadAppEntry
import com.example.dailycheckapp.domain.usecases.appentry.SaveAppEntry
import com.example.dailycheckapp.domain.usecases.news.GetNews
import com.example.dailycheckapp.domain.usecases.news.NewsUseCase
import com.example.dailycheckapp.domain.usecases.news.SearchNews
import com.example.dailycheckapp.onboarding.data.DataStoreRepository
import com.example.dailycheckapp.onboarding.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    @Provides
    @Singleton
    fun provideNewsApi(): NewApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewApi::class.java)
    }




    @Provides
    @Singleton
    fun provideNewsRepository(
        api: NewApi
    ): NewsRepository = NewsRepositoryImpl(api)
    @Provides
    @Singleton
    fun provideNewsUseCase(
        newsRepository: NewsRepository
    ): NewsUseCase {
        return NewsUseCase(
            getNews = GetNews(newsRepository),
          searchNews = SearchNews(newsRepository)
        )
    }

}