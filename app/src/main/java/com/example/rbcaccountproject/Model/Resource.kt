package com.example.rbcaccountproject.Model

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean? = null

) {

    class Success<T>(data: T) : Resource<T>(data = data)

    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)

    class Loading<T>(isLoading: Boolean) : Resource<T>(isLoading = isLoading)
}