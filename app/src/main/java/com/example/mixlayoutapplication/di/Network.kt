package com.example.mixlayoutapplication.di

import com.example.mixlayoutapplication.services.NationalParkServices
import com.example.mixlayoutapplication.util.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Network {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        val client = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideNationalParkServices(retrofit: Retrofit): NationalParkServices {
        return retrofit.create(NationalParkServices::class.java)
    }
}