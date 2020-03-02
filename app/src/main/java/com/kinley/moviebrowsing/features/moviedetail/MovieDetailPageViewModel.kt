package com.kinley.moviebrowsing.features.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.data.repository.MovieBrowsingRemoteImpl
import com.kinley.jetpackui.jetcompose.components.*
import com.kinley.jetpackui.jetcompose.components.UIComponent
import kotlinx.coroutines.launch

class MovieDetailPageViewModel : ViewModel() {

    private val repository = MovieBrowsingRemoteImpl()

    private val _pageData: MutableLiveData<MovieDetailPageUiModel> = MutableLiveData()
    val pageData: LiveData<MovieDetailPageUiModel>
        get() {
            return _pageData
        }

    fun load(id: Long) {
        viewModelScope.launch {
            loadMovieDetails(id)
            loadRecommendedMovies(id)
            loadSimilarMovies(id)
            loadCastAndCrew(id)
        }
    }

    private fun loadMovieDetails(id: Long) {
        viewModelScope.launch {
            val _movie = repository.getMovieDetails(id)
            val movieDetailUIComponent = MovieDetailUIComponent(_movie)
            setUIData(getPageDataValue().copy(movieDetailUIComponent = movieDetailUIComponent))
        }

    }

    private fun loadCastAndCrew(id: Long) {
        viewModelScope.launch {
            val credits = repository.getCredits(id)
            val castMembers = credits.cast.filter { it.profile_path != null }
            val crewMembers = credits.crew.filter { it.profile_path != null }



            setUIData(
                getPageDataValue().copy(
                    castUIComponent = CastUIComponent(castMembers),
                    crewUIComponent = CrewUIComponent(crewMembers)
                )
            )

        }
    }

    private fun loadRecommendedMovies(id: Long) {
        viewModelScope.launch {
            val recommendedMovies = repository.getRecommendedMovies(id).movies
            setUIData(
                getPageDataValue().copy(
                    recommendedMoviesListUIComponent = MovieListUIComponent(
                        subHeader = "Recommended movies",
                        movieList = recommendedMovies
                    )
                )
            )
        }

    }

    private fun loadSimilarMovies(id: Long) {
        viewModelScope.launch {
            val similarMovies = repository.getSimilarMovies(id).movies
            setUIData(
                getPageDataValue().copy(
                    similarMoviesListUIComponent = MovieListUIComponent(
                        subHeader = "Similar movies",
                        movieList = similarMovies
                    )
                )
            )
        }
    }

    /**
     * @param block : This takes the lambda which updates the pageState
     */
/*private fun updateState(block: (MovieDetailPageUiModel) -> MovieDetailPageUiModel) {
    val uiModel = _pageData.value ?: MovieDetailPageUiModel(
      movieDetailUIComponent = null,
      crewUIComponent = null,
      castUIComponent = null,
      recommendedMoviesListUIComponent = null,
      similarMoviesListUIComponent = null
    )
    _pageData.postValue(block(uiModel))

}
*/

    /**
     * Work around for updateState as it caused some local return issues
     */
    private fun getPageDataValue(): MovieDetailPageUiModel =
        _pageData.value ?: MovieDetailPageUiModel(
            movieDetailUIComponent = null,
            crewUIComponent = null,
            castUIComponent = null,
            recommendedMoviesListUIComponent = null,
            similarMoviesListUIComponent = null
        )

    private fun setUIData(movieDetailPageUiModel: MovieDetailPageUiModel) {
        _pageData.value = movieDetailPageUiModel
    }
}


data class MovieDetailPageUiModel(
    val movieDetailUIComponent: MovieDetailUIComponent?,
    val crewUIComponent: CrewUIComponent?,
    val castUIComponent: CastUIComponent?,
    val recommendedMoviesListUIComponent: MovieListUIComponent?,
    val similarMoviesListUIComponent: MovieListUIComponent?
) {

    /**
     * All the components that needs to be rendered are filtered here.
     * The order of the UIComponents are decided here
     */
    fun components(): ArrayList<UIComponent<UIDelegate>> {
        val cmps = arrayListOf<UIComponent<out UIDelegate>>()

        cmps.addNonNull(movieDetailUIComponent)
        cmps.addNonNull(crewUIComponent)
        cmps.addNonNull(castUIComponent)
        cmps.addNonNull(recommendedMoviesListUIComponent)
        cmps.addNonNull(similarMoviesListUIComponent)

        return cmps as ArrayList<UIComponent<UIDelegate>>
    }
}

fun ArrayList<UIComponent<*>>.addNonNull(UIComponent: UIComponent<*>?): List<UIComponent<*>> {
    if (UIComponent == null) return this
    add(UIComponent)
    return this
}