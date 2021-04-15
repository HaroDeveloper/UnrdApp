package com.example.unrd.data.model

import com.google.gson.annotations.SerializedName

data class Image (
    @SerializedName("resource_uri")
    var characterImage: String
)