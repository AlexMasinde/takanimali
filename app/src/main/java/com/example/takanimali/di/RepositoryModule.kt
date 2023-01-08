package com.example.takanimali.di

import com.example.takanimali.data.*
import com.example.takanimali.data.local.LocalAuthRepository
import com.example.takanimali.data.local.LocalAuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    //Network repositories
    @Binds
    @Singleton
    abstract fun bindNetworkAuthRepository(networkAuthRepositoryImpl: NetworkAuthRepositoryImpl): NetworkAuthRepository

    @Binds
    @Singleton
    abstract fun bindRegisterRepository(registerRepositoryImpl: RegisterRepositoryImpl): RegisterRepository

    @Binds
    @Singleton
    abstract fun bindCollectWasteRepository(collectWasteRepositoryImpl: CollectWasteRepositoryImpl): CollectWasteRepository

    @Binds
    @Singleton
    abstract fun bindCollectionHistoryRepository(collectionHistoryRepositoryImpl: CollectionHistoryRepositoryImpl): CollectionHistoryRepository

    @Binds
    @Singleton
    abstract fun bindPointsRepository(pointsRepositoryImpl: PointsRepositoryImpl): PointsRepository

    //Local Repositories
    @Binds
    @Singleton
    abstract fun bindLocalAuthRepository(localAuthRepositoryImpl: LocalAuthRepositoryImpl): LocalAuthRepository



}