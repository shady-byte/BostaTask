package com.example.bostatask.data.repository

import com.example.bostatask.data.api.UserApi
import com.example.bostatask.domain.repository.UserRepository
import com.example.bostatask.model.model.AlbumData
import com.example.bostatask.model.model.PhotoData
import com.example.bostatask.model.model.UserPersonalData
import com.example.bostatask.model.response.ApiResponse

class UserRepositoryImp(private val userAPi: UserApi) : UserRepository {

    override suspend fun getUserPersonalData(userId: Int): ApiResponse<UserPersonalData> {
        return try {
            val response = userAPi.getUserPersonalData(userId)
            ApiResponse.OnSuccess(response)
        } catch (ex: Exception) {
            ApiResponse.OnError(ex)
        }
    }

    override suspend fun getUserAlbums(userId: Int): ApiResponse<List<AlbumData>> {
        return try {
            val response = userAPi.getUserAlbums(userId)
            ApiResponse.OnSuccess(response)
        } catch (ex: Exception) {
            ApiResponse.OnError(ex)
        }
    }

    override suspend fun getAlbumPhotos(albumId: Int): ApiResponse<List<PhotoData>> {
        return try {
            val response = userAPi.getAlbumPhotos(albumId)
            ApiResponse.OnSuccess(response)
        } catch (ex: Exception) {
            ApiResponse.OnError(ex)
        }
    }
}