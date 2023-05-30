package com.herald.productviewer.di

import android.content.Context
import com.herald.productviewer.common.Constants
import com.herald.productviewer.data.remote.RetroService
import com.herald.productviewer.data.remote.repository.RetroImpl
import com.herald.productviewer.domain.repository.RetroRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {


    @Singleton
    @Provides
    fun getRetrofitInstance(@ApplicationContext context:Context ): Retrofit {
        val cacheSize = 10 * 1024 * 1024 // 10MB cache size
        val cacheDirectory = File(context.cacheDir, "http_cache")
        val cache = Cache(cacheDirectory, cacheSize.toLong())

        val client = OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .callTimeout(90, TimeUnit.SECONDS)
            .cache(cache)
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

   @Singleton
    @Provides
    fun getRestApiService(retrofit: Retrofit): RetroService {
        return retrofit.create(RetroService::class.java)
    }

    @Singleton
    @Provides
    fun getRestApiRepo(retroService: RetroService): RetroRepo {
        return RetroImpl(retroService)
    }
/*
    @Singleton
    @Provides
    fun getChatHistoryDB(app: Application): ChatHistoryDatabase {
        return Room.databaseBuilder(
            app, ChatHistoryDatabase::class.java, ChatHistoryDatabase.DB_Name
        ).build()
    }

    @Singleton
    @Provides
    fun getChatHistoryRepo(db: ChatHistoryDatabase): ChatHistoryRepo {
        return ChatHistoryImpl(db.chatHistoryDao())
    }

    @Provides
    fun getContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Singleton
    @Provides
    fun getFireStoreDB(): FirebaseFirestore {
        val database = Firebase.firestore
        database.firestoreSettings = firestoreSettings {
            isPersistenceEnabled = false
        }
        return database
    }

    @Singleton
    @Provides
    fun getFirebaseAuth(): FirebaseAuth {
        return Firebase.auth
    }*/
}