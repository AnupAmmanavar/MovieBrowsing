package com.kinley.moviebrowsing.jetcompose.components


import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.jetcompose.jetpack_views.ComposableView
import com.kinley.moviebrowsing.jetcompose.jetpack_views.MovieDetailView

class MovieDetailUIComponent(
    private val movie: Movie
) : UIComponent<MovieDelegate> {

    override fun composableView(delegate: MovieDelegate): ComposableView {
        return  {
            MovieDetailView(movie = movie, delegate = delegate)
        }
    }
}

interface MovieDelegate : UIDelegate {
    fun onMovieClick(movie: Movie)
}