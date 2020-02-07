package com.kinley.moviebrowsing.features.moviedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.moviebrowsing.models.Movie
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.launch

class MovieDetailPageViewModel : ViewModel() {

    private val repository = MovieBrowsingRemoteImpl()

    val movie: MutableLiveData<Movie> = MutableLiveData()

    fun load(id: Long) {
        viewModelScope.launch {
            val _movie = repository.getMovieDetails(id)
            movie.postValue(_movie)
            Log.d("Movie", movie.toString())
        }

        viewModelScope.launch {
            repository
        }

    }
    // TODO: Implement the ViewModel
}
