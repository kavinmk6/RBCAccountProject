package com.example.rbcaccountproject.Model

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val isLoading: Boolean? = null

    ) {

    // We'll wrap our data in this 'Success'
    class Success<T>(data: T) : Resource<T>(data = data)

    // We'll pass error message wrapped in this 'Error'
    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)

    class Loading <T>(isLoading: Boolean): Resource<T>(isLoading = isLoading)
}