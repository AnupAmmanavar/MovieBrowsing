package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel
import com.kinley.moviebrowsing.MovieDetailBindingModel_
import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.jetcompose.uicomponents.ComposableView
import com.kinley.moviebrowsing.jetcompose.uicomponents.MovieView

class MovieDetailUIComponent(
    override val data: Movie,
    private val movie: Movie = data
) : UIComponent<Movie, MovieDelegate> {

    override fun render(delegate: MovieDelegate): List<EpoxyModel<*>> {
        return arrayListOf(
            MovieDetailBindingModel_()
                .id(movie.id)
                .movie(movie)
        )
    }

    override fun composableView(delegate: MovieDelegate): ComposableView {
        return  {
            MovieView(movie = movie, delegate = delegate)
        }
    }
}

interface MovieDelegate : UIDelegate {
    fun onMovieClick(movie: Movie)
}