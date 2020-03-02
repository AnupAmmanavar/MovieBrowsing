package com.kinley.moviebrowsing.components


import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.jetcompose.uicomponents.ComposableView
import com.kinley.moviebrowsing.jetcompose.uicomponents.JetView
import com.kinley.moviebrowsing.jetcompose.uicomponents.MovieDetailView

class MovieDetailUIComponent(
    private val movie: Movie
) : JetView<MovieDelegate> {

    override fun composableView(delegate: MovieDelegate): ComposableView {
        return  {
            MovieDetailView(movie = movie, delegate = delegate)
        }
    }
}

interface MovieDelegate : UIDelegate {
    fun onMovieClick(movie: Movie)
}