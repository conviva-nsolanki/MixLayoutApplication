package com.example.mixlayoutapplication.services

import com.example.mixlayoutapplication.services.response.NationalParkResponse
import com.example.mixlayoutapplication.util.NATIONAL_PARK_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NationalParkServices {
    @GET("parks")
    suspend fun getNationalParkByStateCodes(@Query("stateCode") stateCode: String, @Query("fields") fields: String = "images", @Query("api_key") apiKey: String = NATIONAL_PARK_API_KEY): NationalParkResponse
}