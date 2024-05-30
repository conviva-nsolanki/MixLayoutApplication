package com.example.mixlayoutapplication.services

import com.example.mixlayoutapplication.services.response.NationalParkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NationalParkServices {
    @GET("parks")
    suspend fun getNationalParkByStateCodes(@Query("stateCode") stateCode: String, @Query("fields") fields: String = "images", @Query("api_key") apiKey: String = "zHwIyMrPk6vBjE2s7vH9YUBDi7spkTJcuTfvwJlY"): NationalParkResponse
}