package com.kinley.moviebrowsing.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.moviebrowsing.models.Movie
import com.kinley.moviebrowsing.models.PopularMovies
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.launch

class HomePageViewModel : ViewModel() {

  private var movies: List<Movie> = mutableListOf()
  private val repository = MovieBrowsingRemoteImpl()


  fun load() {
    viewModelScope.launch {
      movies = repository.getPopularMovies().movies
      Log.d("movies", movies.toString())
    }
  }
}