package com.example.bostatask.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostatask.domain.useCase.GetAlbumPhotosUseCase
import com.example.bostatask.domain.useCase.GetUserAlbumsUseCase
import com.example.bostatask.domain.useCase.GetUserPersonalDataUseCase
import com.example.bostatask.model.model.AlbumData
import com.example.bostatask.model.model.PhotoData
import com.example.bostatask.model.model.UserPersonalData
import com.example.bostatask.model.response.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val getUserPersonalDataUseCase: GetUserPersonalDataUseCase,
    private val getUserAlbumsUseCase: GetUserAlbumsUseCase,
    private val getAlbumPhotosUseCase: GetAlbumPhotosUseCase
) : ViewModel() {

    val userPersonalDataResponse = MutableLiveData<ApiResponse<UserPersonalData>>()
    val userAlbumsResponse = MutableLiveData<ApiResponse<List<AlbumData>>>()
    val albumPhotosResponse = MutableLiveData<ApiResponse<List<PhotoData>>>()
    var albumPhotos = emptyList<PhotoData>()
    private var userId: Int = 1

    fun getUserPersonalData() {
        userId = generateRandomNumber()
        viewModelScope.launch {
            userPersonalDataResponse.postValue(ApiResponse.IsLoading)
            val response = getUserPersonalDataUseCase.getUserPersonalData(userId)
            userPersonalDataResponse.postValue(response)
        }
    }

    fun getUserAlbums() {
        viewModelScope.launch {
            userAlbumsResponse.postValue(ApiResponse.IsLoading)
            val response = getUserAlbumsUseCase.getUserAlbums(userId)
            userAlbumsResponse.postValue(response)
        }
    }

    fun getAlbumPhotos(albumId: Int) {
        viewModelScope.launch {
            albumPhotosResponse.postValue(ApiResponse.IsLoading)
            val response = getAlbumPhotosUseCase.getAlbumPhotos(albumId)
            albumPhotosResponse.postValue(response)
        }
    }

    private fun generateRandomNumber(): Int {
        return Random.nextInt(1, 11)
    }
}