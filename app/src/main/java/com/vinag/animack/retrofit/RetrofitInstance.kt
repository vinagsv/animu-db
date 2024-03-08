package com.vinag.animack.retrofit

import com.vinag.animack.api.MALapi
import com.vinag.animack.api.YoutubeAPI
import com.vinag.animack.utils.Constants.Companion.MAL_BASE_URL
import com.vinag.animack.utils.Constants.Companion.YOUTUBE_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    val malAPI: MALapi by lazy{
        Retrofit.Builder()
            .baseUrl(MAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MALapi::class.java)

    }

    val youtubeAPI : YoutubeAPI by lazy {
        Retrofit.Builder()
            .baseUrl(YOUTUBE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YoutubeAPI::class.java)
    }

}