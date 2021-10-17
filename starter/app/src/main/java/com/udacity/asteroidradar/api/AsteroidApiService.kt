package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofit per lesson 3.7
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create()) // TODO(use both for asteroids and picture of the day?)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL)
    .build()

//per lesson 3.7
interface AsteroidApiService {
    @GET("neo/rest/v1/feed")
    suspend fun getProperties(
        @Query("start_date") startDate: String = "2021-10-15",
        @Query("end_date") endDate: String = "2021-10-22",
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): String

    @GET("planetary/apod")
    suspend fun getImage(@Query("api_key") apiKey: String = Constants.API_KEY): PictureOfDay
}

//3.7
object AsteroidApi {
    val retrofitService: AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
}