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

    val movie: MutableLiveData<Movie> = MutableLiveData()

    val cast: MutableLiveData<List<Cast>> = MutableLiveData()

    val crew: MutableLiveData<List<Crew>> = MutableLiveData()

    fun load(id: Long) {
        viewModelScope.launch {
            val _movie = repository.getMovieDetails(id)
            movie.postValue(_movie)
        }

        viewModelScope.launch {
            val credits = repository.getCredits(id)
            val castMembers = credits.cast.filter { it.profile_path != null }
            val crewMembers = credits.crew.filter { it.profile_path != null }

            cast.postValue(castMembers)
            crew.postValue(crewMembers)
        }

    }
}
