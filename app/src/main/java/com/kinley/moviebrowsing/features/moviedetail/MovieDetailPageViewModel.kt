package com.kinley.moviebrowsing.features.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.moviebrowsing.components.CastUIComponent
import com.kinley.moviebrowsing.components.CrewUIComponent
import com.kinley.moviebrowsing.components.MovieDetailUIComponent
import com.kinley.moviebrowsing.components.MovieListUIComponent
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieDetailPageViewModel : ViewModel() {

    private val repository = MovieBrowsingRemoteImpl()

    val pageData: MutableLiveData<MovieDetailPageUiModel> = MutableLiveData()

    fun load(id: Long) {
        loadMovieDetails(id)
        loadCastAndCrew(id)
        loadRecommendedMovies(id)
        loadSimilarMovies(id)
    }

    private fun loadMovieDetails(id: Long) {
        viewModelScope.launch {
            val _movie = repository.getMovieDetails(id)
            val movieDetailUIComponent = MovieDetailUIComponent(_movie)
            updateState { it.copy(movieDetailUIComponent = movieDetailUIComponent) }
        }
    }

    private fun loadCastAndCrew(id: Long) {
        viewModelScope.launch {
            val credits = repository.getCredits(id)
            val castMembers = credits.cast.filter { it.profile_path != null }
            val crewMembers = credits.crew.filter { it.profile_path != null }



            updateState {
                it.copy(
                    castUIComponent = CastUIComponent(castMembers),
                    crewUIComponent = CrewUIComponent(crewMembers)
                )
            }
        }
    }

    private fun loadRecommendedMovies(id: Long) {
        viewModelScope.launch {
            val recommendedMovies = repository.getRecommendedMovies(id).movies
            updateState { it.copy(recommendedMoviesListUIComponent = MovieListUIComponent(recommendedMovies)) }

        }
    }

    private fun loadSimilarMovies(id: Long) {
        viewModelScope.launch {
            val similarMovies = repository.getSimilarMovies(id).movies
            updateState { it.copy(similarMoviesListUIComponent = MovieListUIComponent(similarMovies)) }
        }
    }

    /**
     * @param block : This takes the lambda which updates the pageState
     */
    private fun updateState(block: (MovieDetailPageUiModel) -> MovieDetailPageUiModel) {
        runBlocking {
            val uiModel = pageData.value ?: MovieDetailPageUiModel(
                movieDetailUIComponent = null,
                crewUIComponent = null,
                castUIComponent = null,
                recommendedMoviesListUIComponent = null,
                similarMoviesListUIComponent = null
            )
            pageData.postValue(block.invoke(uiModel))
        }
    }
}


data class MovieDetailPageUiModel(
    val movieDetailUIComponent: MovieDetailUIComponent?,
    val crewUIComponent: CrewUIComponent?,
    val castUIComponent: CastUIComponent?,
    val recommendedMoviesListUIComponent: MovieListUIComponent?,
    val similarMoviesListUIComponent: MovieListUIComponent?
)