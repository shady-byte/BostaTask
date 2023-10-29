package com.example.bostatask.model.response

sealed class ApiResponse<out T> {
    data class OnSuccess<out T>(val data: T) : ApiResponse<T>()
    data class OnError(val exception: Throwable) : ApiResponse<Nothing>()
    object IsLoading : ApiResponse<Nothing>()
}