package com.example.bostatask.domain.repository

import com.example.bostatask.model.model.AlbumData
import com.example.bostatask.model.model.PhotoData
import com.example.bostatask.model.model.UserPersonalData
import com.example.bostatask.model.response.ApiResponse

interface UserRepository {
    suspend fun getUserPersonalData(userId: Int): ApiResponse<UserPersonalData>

    suspend fun getUserAlbums(userId: Int): ApiResponse<List<AlbumData>>

    suspend fun getAlbumPhotos(albumId: Int): ApiResponse<List<PhotoData>>
}