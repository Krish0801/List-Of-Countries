package com.example.listofcountriestask.data.errorHandling

sealed class ApiResult<out T> {
    data class Success<out R>(val value: R): ApiResult<R>()
    data class Failure(
        val message: String?,
        val throwable: Throwable?
    ): ApiResult<Nothing>()
}

