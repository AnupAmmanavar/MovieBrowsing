package com.kinley.jetpackui.jetcompose.components

import com.kinley.data.models.Movie
import com.kinley.jetpackui.jetcompose.jetpack_views.ComposableView
import com.kinley.jetpackui.jetcompose.jetpack_views.HMovieListView

class MovieListUIComponent(
  private val subHeader: String,
  private val movieList: List<Movie>
) : UIComponent<MovieDelegate> {

  override fun composableView(delegate: MovieDelegate): ComposableView = {
    HMovieListView(header = subHeader, movieList = movieList, delegate = delegate)
  }
}