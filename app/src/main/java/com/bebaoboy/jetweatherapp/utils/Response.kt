package com.bebaoboy.jetweatherapp.utils

class Response {
    sealed class Response<T>(val data: T? = null, val message: String? = null) {
        class Loading<T> : Response<T>(message = "...")
        class Success<T>(data: T) : Response<T>(data, message = "✅")
        class Error<T>(message: String) : Response<T>(message = message)
    }
}