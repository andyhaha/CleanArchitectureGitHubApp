package com.andy.network

import android.util.Log
import com.andy.common.toJson
import com.andy.network.data.ApiResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ApiResultCall<T>(
    private val callDelegate: Call<T>,
) : Call<ApiResult<T>> {

    override fun enqueue(callback: Callback<ApiResult<T>>) = callDelegate.enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                Log.d("ApiResultCall", "onResponse() response: $response}")
                response.body()?.let {
                    when {
                        response.isSuccessful -> {
                            callback.onResponse(
                                this@ApiResultCall,
                                Response.success(ApiResult.Success(it))
                            )
                        }

                        else -> {
                            Log.e(
                                "ApiResultCall", "error code1111111 " +
                                        "= ${response.code()}, message = response"
                            )
                            callback.onResponse(
                                this@ApiResultCall,
                                Response.success(
                                    ApiResult.Error(
                                        response.code(),
                                        response.message()
                                    )
                                ),
                            )
                        }
                    }
                } ?: callback.onResponse(
                    this@ApiResultCall,
                    Response.success(
                        ApiResult.Error(
                            response.code(),
                            response.message()
                        )
                    )
                )
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                Log.d("ApiResultCall", "onFailure() throwable: $throwable}")
                callback.onResponse(
                    this@ApiResultCall,
                    Response.success(ApiResult.Exception(throwable))
                )
                call.cancel()
            }
        },
    )

    override fun clone(): Call<ApiResult<T>> = ApiResultCall(callDelegate.clone())

    override fun execute(): Response<ApiResult<T>> =
        throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = callDelegate.isExecuted

    override fun cancel() = callDelegate.cancel()

    override fun isCanceled(): Boolean = callDelegate.isCanceled

    override fun request(): Request = callDelegate.request()

    override fun timeout(): Timeout = callDelegate.timeout()
}