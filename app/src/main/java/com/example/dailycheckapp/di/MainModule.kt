package com.example.dailycheckapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.dailycheckapp.data.LocalUserImpl
import com.example.dailycheckapp.data.local.NewsDao
import com.example.dailycheckapp.data.local.NewsDatabase
import com.example.dailycheckapp.data.local.NewsTypeConvertor
import com.example.dailycheckapp.data.remote.NewApi
import com.example.dailycheckapp.data.repository.NewsRepositoryImpl
import com.example.dailycheckapp.domain.LocalUser
import com.example.dailycheckapp.domain.repository.NewsRepository
import com.example.dailycheckapp.domain.usecases.appentry.AppEntryUseCases
import com.example.dailycheckapp.domain.usecases.appentry.ReadAppEntry
import com.example.dailycheckapp.domain.usecases.appentry.SaveAppEntry
import com.example.dailycheckapp.domain.usecases.news.DeleteArticle
import com.example.dailycheckapp.domain.usecases.news.GetArticle
import com.example.dailycheckapp.domain.usecases.news.GetArticles
import com.example.dailycheckapp.domain.usecases.news.GetNews
import com.example.dailycheckapp.domain.usecases.news.NewsUseCase
import com.example.dailycheckapp.domain.usecases.news.SearchNews
import com.example.dailycheckapp.domain.usecases.news.UpsertArticle
import com.example.dailycheckapp.onboarding.data.DataStoreRepository
import com.example.dailycheckapp.onboarding.util.Constants.BASE_URL
import com.example.dailycheckapp.onboarding.util.Constants.NEWS_DATABASE_NAME
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
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCase {
        return NewsUseCase(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            getArticles = GetArticles(newsDao),
            getArticle = GetArticle(newsDao)
        )
    }
    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao
}