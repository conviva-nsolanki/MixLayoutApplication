package com.example.mixlayoutapplication.di

import android.content.Context
import com.conviva.apptracker.ConvivaAppAnalytics
import com.conviva.apptracker.controller.TrackerController
import com.example.mixlayoutapplication.util.APP_NAME
import com.example.mixlayoutapplication.util.CUATOMER_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideConvivaTracker(@ApplicationContext appContext: Context): TrackerController {
        println("nannandenden provideConvivaTracker")
        return ConvivaAppAnalytics.createTracker(
            appContext,
            CUATOMER_KEY,
            APP_NAME
        )!!
    }

}