package com.kinley.moviebrowsing.features.home

import androidx.lifecycle.*
import com.kinley.moviebrowsing.components.MovieListUIComponent
import com.kinley.moviebrowsing.models.MoviesDataModel
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageViewModel : ViewModel(), LifecycleObserver {

    var pageData: MutableLiveData<HomePageUIModel> = MutableLiveData()
    private val repository = MovieBrowsingRemoteImpl()


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun load() {
        pageData.value = HomePageUIModel()
        moviesLoader { repository.getPopularMovies() }
        moviesLoader { repository.getTopRatedMovies() }
        moviesLoader { repository.getUpcomingMovies() }
        moviesLoader { repository.getPlayingNow() }
    }

    private fun moviesLoader(loader: suspend () -> MoviesDataModel) {
        viewModelScope.launch {
            val movies = loader.invoke().movies
            addMoviesList(MovieListUIComponent(movies))
        }
    }

    private suspend fun addMoviesList(movieListUIComponent: MovieListUIComponent) {
        val updatedMovieListUIComponents = withContext(Dispatchers.Default) {
            val movieListUIComponents =
                pageData.value?.movieListUIComponents ?: arrayListOf()

            movieListUIComponents.add(movieListUIComponent)
            movieListUIComponents
        }
        pageData.postValue(HomePageUIModel(movieListUIComponents = updatedMovieListUIComponents))
    }
}

data class HomePageUIModel(
    val movieListUIComponents: ArrayList<MovieListUIComponent> = arrayListOf()
)