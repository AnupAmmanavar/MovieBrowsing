package com.kinley.moviebrowsing.features.home

import androidx.compose.Model
import androidx.lifecycle.*
import com.kinley.data.models.MoviesDataModel
import com.kinley.data.repository.MovieBrowsingRemoteImpl
import com.kinley.jetpackui.jetcompose.components.MovieListUIComponent
import com.kinley.jetpackui.jetcompose.jetpack_views.ButtonEventDispatcher
import com.kinley.jetpackui.jetcompose.jetpack_views.InputViewComponent
import com.kinley.jetpackui.jetcompose.jetpack_views.SearchButtonComponent
import com.kinley.jetpackui.jetcompose.jetpack_views.UIEventDispatcher
import kotlinx.coroutines.launch

class HomePageViewModel : ViewModel(), LifecycleObserver, UIEventDispatcher, ButtonEventDispatcher {

    var pageData: MutableLiveData<HomePageUIModel> = MutableLiveData()
    private val repository = MovieBrowsingRemoteImpl()

    private val query: MutableLiveData<String> = MutableLiveData()

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun load() {
        pageData.value = HomePageUIModel(InputViewComponent("", this), SearchButtonComponent(this))
        moviesLoader("Popular movies") { repository.getPopularMovies() }
        moviesLoader("Top rated movies") { repository.getTopRatedMovies() }
        moviesLoader("Upcoming movies") { repository.getUpcomingMovies() }
        moviesLoader("Playing now") { repository.getPlayingNow() }
    }

    private fun moviesLoader(category: String, loader: suspend () -> MoviesDataModel) {
        viewModelScope.launch {
            val movies = loader.invoke().movies
            addMoviesList(MovieListUIComponent(category, movies))
        }
    }

    private fun addMoviesList(movieListUIComponent: MovieListUIComponent) {
        val updatedMovieListUIComponents = run {
            val movieListUIComponents =
                pageData.value?.movieListUIComponents ?: arrayListOf()

            movieListUIComponents.add(movieListUIComponent)
            movieListUIComponents
        }
        pageData.postValue(HomePageUIModel(InputViewComponent(query.value, this), SearchButtonComponent(this), movieListUIComponents = updatedMovieListUIComponents))
    }

    override fun onInputChange(input: String) {
        query.postValue(input)
    }

    override fun onSearchClicked() {
        viewModelScope.launch {
            val movieQuery = query.value ?: return@launch
            val movies = repository.getMovies(movieQuery).movies.filter { it.poster_path != null }
            val searchedMovieComponent = MovieListUIComponent("Results for $movieQuery", movies)
            pageData.value = pageData.value?.copy(searchedMovies = searchedMovieComponent)
        }
    }
}

@Model
data class HomePageUIModel(
    val inputViewComponent: InputViewComponent,
    val searchButtonComponent: SearchButtonComponent,
    val searchedMovies: MovieListUIComponent? = null,
    val movieListUIComponents: ArrayList<MovieListUIComponent> = arrayListOf()
)