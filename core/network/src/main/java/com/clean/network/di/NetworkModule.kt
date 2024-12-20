package com.clean.network.di

import androidx.core.os.BuildCompat
import com.clean.network.BuildConfig
import com.clean.network.CaApi
import com.clean.network.datasource.DictionaryDataSource
import com.clean.network.datasource.DictionaryDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(interceptor: HttpLoggingInterceptor): Call.Factory =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

    @Provides
    @Singleton
    fun provideConverterFactory(networkJson: Json): Converter.Factory =
        networkJson.asConverterFactory("application/json".toMediaType())

    @Provides
    @Singleton
    fun provideRetrofit(
        okhttpCallFactory: Call.Factory,
        converterFactory: Converter.Factory
    ): CaApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .callFactory { okhttpCallFactory.newCall(it) }
            .addConverterFactory(converterFactory)
            .build()
            .create(CaApi::class.java)

    @Provides
    @Singleton
    fun provideDataSource(caApi: dagger.Lazy<CaApi>): DictionaryDataSource {
        return DictionaryDataSourceImpl(caApi)
    }
}