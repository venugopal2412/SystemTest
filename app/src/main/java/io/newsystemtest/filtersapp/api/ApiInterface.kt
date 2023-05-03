package io.newsystemtest.filtersapp.api

import io.newsystemtest.filtersapp.api.Request.ProductCategoryRequest
import io.newsystemtest.filtersapp.api.Response.ProductCategoryResponse
import io.newsystemtest.filtersapp.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiInterface {

    @POST(Constants.PRODUCT_CATEGORY_API)
    fun getProductDetails(@Body request: ProductCategoryRequest?): Call<ProductCategoryResponse>


    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL).client(provideOkHttpClient())
                .build()
            return retrofit.create(ApiInterface::class.java)
        }

       private fun provideOkHttpClient(): OkHttpClient {
            val builder = OkHttpClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
            //.addInterceptor(authInterceptor)
            val requestInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addNetworkInterceptor(requestInterceptor)
            return builder.build()
        }
    }
}