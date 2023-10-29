package com.example.bostatask.model.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AlbumData(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String
) : Serializable
