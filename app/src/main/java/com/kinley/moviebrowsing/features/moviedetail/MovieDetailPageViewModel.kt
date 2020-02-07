package com.kinley.moviebrowsing.features.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.moviebrowsing.models.Cast
import com.kinley.moviebrowsing.models.Crew
import com.kinley.moviebrowsing.models.Movie
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.launch

class MovieDetailPageViewModel : ViewModel() {

    private val repository = MovieBrowsingRemoteImpl()

    val pageData: MutableLiveData<MovieDetailPageUiModel> = MutableLiveData()

    fun load(id: Long) {
        loadMovieDetails(id)
        loadCastAndCrew(id)
    }

    private fun loadMovieDetails(id: Long) {
        viewModelScope.launch {
            val _movie = repository.getMovieDetails(id)
            updateState { it.copy(movie = _movie) }
        }
    }

    private fun loadCastAndCrew(id: Long) {
        viewModelScope.launch {
            val credits = repository.getCredits(id)
            val castMembers = credits.cast.filter { it.profile_path != null }
            val crewMembers = credits.crew.filter { it.profile_path != null }

            updateState {
                it.copy(
                    castMembers = castMembers,
                    crewMembers = crewMembers
                )
            }
        }
    }

    private fun updateState(block: (MovieDetailPageUiModel) -> MovieDetailPageUiModel) {
        val uiModel = pageData.value ?: MovieDetailPageUiModel(null, null, null)
        pageData.postValue(block.invoke(uiModel))
    }
}


data class MovieDetailPageUiModel(
    val movie: Movie?,
    val crewMembers: List<Crew>?,
    val castMembers: List<Cast>?
)