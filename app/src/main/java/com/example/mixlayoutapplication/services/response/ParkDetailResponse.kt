package com.example.mixlayoutapplication.services.response

import com.example.mixlayoutapplication.data.NationalPark
import com.google.gson.annotations.SerializedName

data class ParkDetailResponse(
    val url: String,
    @SerializedName("fullName")
    val name: String,
    val description: String,
    val images: List<ParkImagesResponse>
) {
    companion object {
        fun ParkDetailResponse.toNationalPark(): NationalPark {
            return NationalPark(
                url = this.url,
                name = this.name,
                description = this.description,
                imageUrl = this.images.firstOrNull()?.imageUrl ?: ""
            )
        }
    }
}
