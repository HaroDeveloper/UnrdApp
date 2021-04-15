package com.example.unrd.data.model

import com.google.gson.annotations.SerializedName

data class Result(
    var name: String,
    @SerializedName("short_summary")
    var shortSummary: String,
    @SerializedName("full_summary")
    var fullSummary: String,
    @SerializedName("age_from")
    var minAge: String,
    var duration: String,
    @SerializedName("created")
    var creationDate: String,
    @SerializedName("list_image")
    var listImage: List<ListImage>,
    @SerializedName("preview_media")
    var previewMedia: List<PreviewMedia>,
    @SerializedName("background_image")
    var backgroundImage: List<BackgroundImage>,
    var characters: List<Characters>,
    @SerializedName("intro_video")
    var videoList: List<Video>



)