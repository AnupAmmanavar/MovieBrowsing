package com.kinley.data.repository

import com.kinley.data.models.*
import com.kinley.data.setup.RetrofitServiceFactory
import retrofit2.http.Path

interface MovieBrowsingRemote {

  suspend fun getPopularMovies(): MoviesDataModel

  suspend fun getTopRatedMovies(): MoviesDataModel

  suspend fun getUpcomingMovies(): MoviesDataModel

  suspend fun getMovieDetails(id: Long): Movie

  suspend fun getCredits(id: Long): MovieCredits

  suspend fun getRecommendedMovies(id: Long): MoviesDataModel

  suspend fun getSimilarMovies(id: Long): MoviesDataModel

  suspend fun getPlayingNow(): MoviesDataModel

  suspend fun getPersonDetails(id: Long): Person

  suspend fun getMovieCreditsForPerson(id: Long): PersonCastResponseModel

  suspend fun getMovies(query: String): MoviesDataModel
}

class MovieBrowsingRemoteImpl(

) : MovieBrowsingRemote {
  private val service = RetrofitServiceFactory.generateService()

  override suspend fun getPopularMovies(): MoviesDataModel = service.getPopularMovies()

  override suspend fun getTopRatedMovies(): MoviesDataModel = service.getTopRatedMovies()

  override suspend fun getUpcomingMovies(): MoviesDataModel = service.getUpcomingMovies()

  override suspend fun getMovieDetails(id: Long): Movie = service.getMovie(id)

  override suspend fun getCredits(id: Long): MovieCredits = service.getCredits(id)

  override suspend fun getRecommendedMovies(id: Long): MoviesDataModel = service.getMovieRecommendations(id)

  override suspend fun getSimilarMovies(id: Long): MoviesDataModel = service.getSimilarMovies(id)

  override suspend fun getPlayingNow() = service.getNowPlaying()

  override suspend fun getPersonDetails(id: Long) = service.getPersonDetails(id)

  override suspend fun getMovieCreditsForPerson(id: Long) = service.getMovieCreditsForPerson(id)

  override suspend fun getMovies(query: String): MoviesDataModel = service.getMovies(query)
}