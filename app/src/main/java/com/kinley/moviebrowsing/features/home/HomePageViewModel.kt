package com.kinley.moviebrowsing.features.home

import androidx.lifecycle.*
import com.kinley.moviebrowsing.models.Movie
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageViewModel : ViewModel(), LifecycleObserver {

  var pageData: MutableLiveData<MutableList<List<Movie>>> = MutableLiveData()
  private val repository = MovieBrowsingRemoteImpl()


  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  fun load() {
    pageData.value = mutableListOf()
    viewModelScope.launch { setData(repository.getPopularMovies().movies) }
    viewModelScope.launch { setData(repository.getTopRatedMovies().movies) }
    viewModelScope.launch { setData(repository.getUpcomingMovies().movies) }

  }

  private suspend fun setData(movieList: List<Movie>) {
    val list = withContext(Dispatchers.Default) {
      val list = pageData.value ?: mutableListOf()
      list.add(movieList)
      list
    }
    pageData.postValue(list)
  }
}