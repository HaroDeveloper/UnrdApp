package com.example.unrd.utils

import com.example.unrd.utils.wraper.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class Extensions

suspend inline fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    crossinline apiCall: suspend CoroutineScope.() -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke(this))
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResultWrapper.NetworkError

                is HttpException -> ResultWrapper.GenericError(
                    throwable.code(),
                    throwable.message()
                )

                else -> ResultWrapper.GenericError(null, null)
            }
        }
    }
}

inline fun <T> ResultWrapper<T>.getResult(
    crossinline success: (value: T) -> Unit,
    noinline genericError: ((code: Int?, message: String?) -> Unit)? = null,
    noinline networkError: (() -> Unit)? = null
) {

    when (this) {
        is ResultWrapper.Success -> {
            success(value)
        }
        is ResultWrapper.GenericError -> {
            genericError?.let { it(code, errorMessage) }
        }
        is ResultWrapper.NetworkError -> {
            networkError?.let { it() }
        }
    }
}