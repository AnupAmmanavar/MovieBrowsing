package com.kinley.moviebrowsing.features.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.moviebrowsing.models.Movie
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.launch

class HomePageViewModel : ViewModel() {

  var movies: MutableLiveData<List<Movie>> = MutableLiveData()
  private val repository = MovieBrowsingRemoteImpl()


  fun load() {
    viewModelScope.launch {
      movies.postValue(repository.getPopularMovies().movies)
      Log.d("movies", movies.value.toString())
    }
  }
}