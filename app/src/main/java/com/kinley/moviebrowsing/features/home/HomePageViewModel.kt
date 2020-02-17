package com.kinley.moviebrowsing.features.home

import androidx.compose.Model
import androidx.lifecycle.*
import com.kinley.data.models.MoviesDataModel
import com.kinley.data.repository.MovieBrowsingRemoteImpl
import com.kinley.moviebrowsing.components.MovieListUIComponent
import kotlinx.coroutines.launch

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

    private fun addMoviesList(movieListUIComponent: MovieListUIComponent) {
        val updatedMovieListUIComponents = run {
            val movieListUIComponents =
                pageData.value?.movieListUIComponents ?: arrayListOf()

            movieListUIComponents.add(movieListUIComponent)
            movieListUIComponents
        }
        pageData.postValue(HomePageUIModel(movieListUIComponents = updatedMovieListUIComponents))
    }
}

@Model
data class HomePageUIModel(
    val movieListUIComponents: ArrayList<MovieListUIComponent> = arrayListOf()
)