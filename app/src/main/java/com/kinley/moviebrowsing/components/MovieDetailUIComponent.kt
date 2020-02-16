package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel
import com.kinley.moviebrowsing.MovieDetailBindingModel_
import com.kinley.moviebrowsing.models.Movie

class MovieDetailUIComponent(
    val movie: Movie
) : UIComponent<MovieDelegate> {

    override fun render(delegate: MovieDelegate): List<EpoxyModel<*>> {
        return arrayListOf(
            MovieDetailBindingModel_()
                .id(movie.id)
                .movie(movie)
        )
    }
}

interface MovieDelegate : UIDelegate {
    fun onMovieClick(movie: Movie)
}