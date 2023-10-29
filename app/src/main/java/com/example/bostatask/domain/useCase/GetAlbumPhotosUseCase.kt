package com.example.bostatask.domain.useCase

import com.example.bostatask.domain.repository.UserRepository
import com.example.bostatask.model.model.PhotoData
import com.example.bostatask.model.response.ApiResponse
import javax.inject.Inject

class GetAlbumPhotosUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getAlbumPhotos(albumId: Int): ApiResponse<List<PhotoData>> {
        return userRepository.getAlbumPhotos(albumId)
    }
}