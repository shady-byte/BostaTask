package com.example.bostatask.data.api

import com.example.bostatask.BuildConfig

object ApiUrls {
    const val BASE_URL = "https://" + BuildConfig.API_BASE_URL + "/"
    const val USER_PERSONAL_DATA_URL = "users/{user_id}"
    const val USER_ALBUMS_URL = "albums"
    const val ALBUM_PHOTOS_URL = "photos"
}