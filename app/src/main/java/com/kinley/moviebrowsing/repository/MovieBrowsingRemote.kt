package com.kinley.moviebrowsing.repository

import com.kinley.moviebrowsing.models.Movie
import com.kinley.moviebrowsing.models.MovieCredits
import com.kinley.moviebrowsing.models.PopularMovies
import com.kinley.moviebrowsing.setup.RetrofitServiceFactory

interface MovieBrowsingRemote {

  suspend fun getPopularMovies(): PopularMovies

  suspend fun getTopRatedMovies(): PopularMovies

  suspend fun getUpcomingMovies(): PopularMovies

  suspend fun getMovieDetails(id: Long): Movie

  suspend fun getCredits(id: Long): MovieCredits
}

class MovieBrowsingRemoteImpl(

) : MovieBrowsingRemote {
  val service = RetrofitServiceFactory.generateService()

  override suspend fun getPopularMovies(): PopularMovies = service.getPopularMovies()

  override suspend fun getTopRatedMovies(): PopularMovies = service.getTopRatedMovies()

  override suspend fun getUpcomingMovies(): PopularMovies = service.getUpcomingMovies()

  override suspend fun getMovieDetails(id: Long): Movie = service.getMovie(id)

  override suspend fun getCredits(id: Long): MovieCredits = service.getCredits(id)
}