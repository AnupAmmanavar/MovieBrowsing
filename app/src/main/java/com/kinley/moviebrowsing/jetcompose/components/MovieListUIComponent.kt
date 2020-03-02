package com.kinley.moviebrowsing.jetcompose.components

import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.jetcompose.jetpack_views.ComposableView
import com.kinley.moviebrowsing.jetcompose.jetpack_views.HMovieListView

class MovieListUIComponent(
    private val movieList: List<Movie>
) : UIComponent<MovieDelegate> {

    override fun composableView(delegate: MovieDelegate): ComposableView = {
        HMovieListView(movieList = movieList, delegate = delegate)
    }
}