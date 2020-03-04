package com.kinley.moviebrowsing.features.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.data.repository.MovieBrowsingRemote
import com.kinley.data.repository.MovieBrowsingRemoteImpl
import com.kinley.jetpackui.jetcompose.jetpack_views.InputViewComponent
import com.kinley.jetpackui.jetcompose.jetpack_views.SearchedMoviesListComponent
import com.kinley.jetpackui.jetcompose.jetpack_views.UIEventDispatcher
import kotlinx.coroutines.launch

class SearchPageViewModel : ViewModel(), UIEventDispatcher {

    private val movieBrowsingRemote: MovieBrowsingRemote = MovieBrowsingRemoteImpl()

    val pageData: MutableLiveData<SearchPageUIModel> = MutableLiveData()

    init {
        pageData.value = SearchPageUIModel(InputViewComponent("", this), null)
    }

    private fun loadMovies(searchQuery: String) {
        if (searchQuery.length < 3)
            return
        
        viewModelScope.launch {
            val movies = movieBrowsingRemote.getMovies(searchQuery).movies.filter { it.poster_path != null }
            val movieListUIComponent = SearchedMoviesListComponent("Results for $searchQuery", movies)
            pageData.value = (pageData.value!!.copy(inputViewComponent = InputViewComponent(searchQuery, this@SearchPageViewModel), searchedMoviesListComponent = movieListUIComponent))
        }
    }

    override fun onInputChange(input: String) {
        loadMovies(input)
    }


}

data class SearchPageUIModel(
    val inputViewComponent: InputViewComponent,
    val searchedMoviesListComponent: SearchedMoviesListComponent?
)