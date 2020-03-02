package com.kinley.moviebrowsing.components

import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.jetcompose.uicomponents.ComposableView
import com.kinley.moviebrowsing.jetcompose.uicomponents.HMovieListView
import com.kinley.moviebrowsing.jetcompose.uicomponents.JetView

class MovieListUIComponent(
    private val movieList: List<Movie>
) : JetView<MovieDelegate> {

    override fun composableView(delegate: MovieDelegate): ComposableView = {
        HMovieListView(movieList = movieList, delegate = delegate)
    }
}