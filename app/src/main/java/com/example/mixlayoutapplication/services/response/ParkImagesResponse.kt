package com.example.mixlayoutapplication.services.response

import com.google.gson.annotations.SerializedName

data class ParkImagesResponse(
    @SerializedName("url")
    val imageUrl: String
)
