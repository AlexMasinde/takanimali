package com.dca.takanimali.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.dca.takanimali.data.*
import com.dca.takanimali.data.local.*
import com.dca.takanimali.network.ApiService
import com.dca.takanimali.ui.auth.AuthViewModel
import com.dca.takanimali.ui.collect.CollectViewModel
import com.dca.takanimali.ui.collection.CollectionViewModel
import com.dca.takanimali.ui.points.PointsViewModel
import com.dca.takanimali.ui.register.RegisterViewModel
import com.dca.takanimali.ui.report.ReportViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val BASE_URL = "https://dcatakanimali.co.ke/api/"

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(httpClient)
            .build()
            .create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TakaNiMaliDatabase::class.java, "taka_ni_mali_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideUserDao(takaNiMaliDatabase: TakaNiMaliDatabase): UserDao {
        return takaNiMaliDatabase.userDao()
    }

    @Provides
    @Singleton
    fun providesHistoryDao(takaNiMaliDatabase: TakaNiMaliDatabase): HistoryDao {
        return takaNiMaliDatabase.historyDao()
    }

    @Provides
    @Singleton
    fun providesPointsDao(takaNiMaliDatabase: TakaNiMaliDatabase): PointsDao {
        return takaNiMaliDatabase.pointsDao()
    }

    @Provides
    @Singleton
    fun provideStateHandler(): SavedStateHandle {
        return SavedStateHandle()
    }


    //Models
    @Provides
    @Singleton
    fun providesAuthViewModel(
        networkAuthRepository: NetworkAuthRepository,
        localAuthRepository: LocalAuthRepository,
        state: SavedStateHandle
    ): AuthViewModel {
        return AuthViewModel(networkAuthRepository, localAuthRepository, state)
    }

    @Provides
    @Singleton
    fun providesPointsViewModel(
        pointsRepository: PointsRepository,
        localPointsRepository: LocalPointsRepository,
        state: SavedStateHandle
    ): PointsViewModel {
        return PointsViewModel(pointsRepository, localPointsRepository, state)
    }

    @Provides
    @Singleton
    fun providesCollectionViewModel(
        collectionHistoryRepository: CollectionHistoryRepository,
        localCollectionRepository: LocalCollectionRepository,
        state: SavedStateHandle
    ): CollectionViewModel {
        return CollectionViewModel(collectionHistoryRepository, localCollectionRepository, state)
    }

    @Provides
    @Singleton
    fun providesRegisterViewModel(registerRepository: RegisterRepository): RegisterViewModel {
        return RegisterViewModel(registerRepository)
    }

    @Provides
    @Singleton
    fun providesCollectViewModel(collectWasteRepository: CollectWasteRepository): CollectViewModel {
        return CollectViewModel(collectWasteRepository)
    }

    @Provides
    @Singleton
    fun providesReportViewModel(collectWasteRepository: CollectWasteRepository): ReportViewModel {
        return ReportViewModel(collectWasteRepository)
    }
}