package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel
import com.kinley.moviebrowsing.MovieCellBindingModel_
import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.jetcompose.uicomponents.ComposableView
import com.kinley.moviebrowsing.jetcompose.uicomponents.HMovieListView

class MovieListUIComponent(
    override val data: List<Movie>,
    private val movieList: List<Movie> = data
) : UIComponent<List<Movie>, MovieDelegate> {

    override fun render(delegate: MovieDelegate): List<EpoxyModel<*>> {
        val movieModels: ArrayList<MovieCellBindingModel_> = arrayListOf()

        movieList.forEach { movie ->
            movieModels.add(
                MovieCellBindingModel_()
                    .id(movie.id)
                    .movie(movie)
                    .onClick { _ ->
                        delegate.onMovieClick(movie)
                    }
            )
        }
        return movieModels
    }

    override fun composableView(delegate: MovieDelegate): ComposableView = {
        HMovieListView(movieListUIComponent = this, delegate = delegate)
    }
}