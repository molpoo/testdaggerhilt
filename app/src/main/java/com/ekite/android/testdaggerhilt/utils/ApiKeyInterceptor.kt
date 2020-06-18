package com.ekite.android.testdaggerhilt.utils

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl = request.url().newBuilder().addQueryParameter("api_key", "wiYRjVjorjiNUqZD21FXJv5JXZXTfbYjsIIFxOtJ").build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}