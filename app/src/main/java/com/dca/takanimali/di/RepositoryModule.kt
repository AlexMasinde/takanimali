package com.dca.takanimali.di

import com.dca.takanimali.data.*
import com.dca.takanimali.data.local.*
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

    @Binds
    @Singleton
    abstract fun bindLocalCollectionRepository(localCollectionRepositoryImpl: LocalCollectionRepositoryImpl): LocalCollectionRepository

    @Binds
    @Singleton
    abstract fun bindLocalPointsRepository(localPointsRepositoryImpl: LocalPointsRepositoryImpl): LocalPointsRepository


}