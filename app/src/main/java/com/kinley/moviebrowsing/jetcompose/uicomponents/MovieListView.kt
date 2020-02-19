package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.Spacer
import androidx.ui.unit.dp
import com.kinley.moviebrowsing.components.MovieDelegate
import com.kinley.moviebrowsing.components.MovieListUIComponent
import com.kinley.moviebrowsing.jetcompose.HStack

@Composable
fun HMovieListView(movieListUIComponent: MovieListUIComponent, delegate: MovieDelegate) {
    HStack {
        movieListUIComponent.data.forEach { movie ->
            Spacer(modifier = LayoutSize(16.dp, 0.dp))
            MovieView(movie = movie, delegate = delegate)
        }

    }
}