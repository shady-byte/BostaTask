package com.example.bostatask.domain.useCase

import com.example.bostatask.domain.repository.UserRepository
import com.example.bostatask.model.model.UserPersonalData
import com.example.bostatask.model.response.ApiResponse
import javax.inject.Inject

class GetUserPersonalDataUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun getUserPersonalData(userId: Int): ApiResponse<UserPersonalData> {
        return userRepository.getUserPersonalData(userId)
    }
}