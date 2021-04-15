package com.example.unrd.data.model

import com.google.gson.annotations.SerializedName

data class PreviewMedia(
    @SerializedName("resource_uri")
    var videoUrl: String
)