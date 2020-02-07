package com.kinley.moviebrowsing.repository

import com.kinley.moviebrowsing.models.Movie
import com.kinley.moviebrowsing.models.MovieCredits
import com.kinley.moviebrowsing.models.PopularMovies
import retrofit2.http.GET
import retrofit2.http.Path

const val API_KEY = "api_key=1d9b898a212ea52e283351e521e17871"

interface MovieBrowsing {

  @GET("movie/popular?$API_KEY")
  suspend fun getPopularMovies(): PopularMovies

  @GET("movie/top_rated?$API_KEY")
  suspend fun getTopRatedMovies(): PopularMovies

  @GET("movie/upcoming?$API_KEY")
  suspend fun getUpcomingMovies(): PopularMovies

  @GET("movie/{id}?$API_KEY")
  suspend fun getMovie(@Path("id") id: Long): Movie

  @GET("movie/{id}/credits?$API_KEY")
  suspend fun getCredits(@Path("id") id: Long): MovieCredits

  @GET("movie/{id}/recommendations?$API_KEY")
  suspend fun getMovieRecommendations(@Path("id") id: Long): PopularMovies

  @GET("movie/{id}/similar?$API_KEY")
  suspend fun getSimilarMovies(@Path("id") id: Long): PopularMovies

}