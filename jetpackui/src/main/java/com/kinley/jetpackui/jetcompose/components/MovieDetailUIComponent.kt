package com.kinley.jetpackui.jetcompose.components


import com.kinley.data.models.Movie
import com.kinley.jetpackui.jetcompose.jetpack_views.ComposableView
import com.kinley.jetpackui.jetcompose.jetpack_views.MovieDetailView

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