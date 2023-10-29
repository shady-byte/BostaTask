package com.example.bostatask.data.api

import com.example.bostatask.model.model.AlbumData
import com.example.bostatask.model.model.PhotoData
import com.example.bostatask.model.model.UserPersonalData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET(ApiUrls.USER_PERSONAL_DATA_URL)
    suspend fun getUserPersonalData(
        @Path("user_id") userId: Int
    ): UserPersonalData

    @GET(ApiUrls.USER_ALBUMS_URL)
    suspend fun getUserAlbums(
        @Query("userId") userId: Int
    ): List<AlbumData>

    @GET(ApiUrls.ALBUM_PHOTOS_URL)
    suspend fun getAlbumPhotos(
        @Query("albumId") albumId: Int
    ): List<PhotoData>

}