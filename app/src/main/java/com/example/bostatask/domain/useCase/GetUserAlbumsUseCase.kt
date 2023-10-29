package com.example.bostatask.domain.useCase

import com.example.bostatask.domain.repository.UserRepository
import com.example.bostatask.model.model.AlbumData
import com.example.bostatask.model.response.ApiResponse
import javax.inject.Inject

class GetUserAlbumsUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getUserAlbums(userId: Int): ApiResponse<List<AlbumData>> {
        return userRepository.getUserAlbums(userId)
    }
}