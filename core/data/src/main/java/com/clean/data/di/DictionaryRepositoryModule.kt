package com.clean.data.di

import com.clean.data.repository.DictionaryRepositoryImpl
import com.clean.domain.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DictionaryRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repository: DictionaryRepositoryImpl): DictionaryRepository
}

