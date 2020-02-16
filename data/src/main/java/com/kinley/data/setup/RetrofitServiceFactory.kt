package com.kinley.data.setup

import com.google.gson.Gson
import com.kinley.data.repository.MovieBrowsing
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServiceFactory {
  fun generateService(): MovieBrowsing {
    val okHttpClient = makeOkHttpClient()
    return generateService(okHttpClient, Gson())
  }

  private fun generateService(okHttpClient: OkHttpClient, gson: Gson): MovieBrowsing {
    val retrofit = Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/3/")
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
    return retrofit.create(MovieBrowsing::class.java)
  }

  private fun makeOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .connectTimeout(120, TimeUnit.SECONDS)
      .readTimeout(120, TimeUnit.SECONDS)
      .build()
  }
}