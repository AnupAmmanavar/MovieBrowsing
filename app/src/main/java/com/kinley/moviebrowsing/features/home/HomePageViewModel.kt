package com.kinley.moviebrowsing.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.moviebrowsing.models.Movie
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageViewModel : ViewModel() {


  var movies: MutableLiveData<List<Movie>> = MutableLiveData()
  var appData: MutableLiveData<MutableList<List<Movie>>> = MutableLiveData()
  private val repository = MovieBrowsingRemoteImpl()


  fun load() {
    appData.value = mutableListOf()
    viewModelScope.launch { setData(repository.getPopularMovies().movies) }
    viewModelScope.launch { setData(repository.getTopRatedMovies().movies) }
    viewModelScope.launch { setData(repository.getUpcomingMovies().movies) }

  }

    private suspend fun setData(movieList: List<Movie>) {
        val list = withContext(Dispatchers.Default) {
            val list = appData.value ?: mutableListOf()
            list.add(movieList)
            list
        }
        appData.postValue(list)
    }
}