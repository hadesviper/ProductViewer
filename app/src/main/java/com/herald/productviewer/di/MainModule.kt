package com.herald.productviewer.di

import com.herald.productviewer.common.Constants
import com.herald.productviewer.data.remote.RetroService
import com.herald.productviewer.data.remote.repository.RetroImpl
import com.herald.productviewer.domain.repository.RetroRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .callTimeout(90, TimeUnit.SECONDS)
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