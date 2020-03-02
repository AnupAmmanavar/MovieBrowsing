package com.kinley.moviebrowsing.jetcompose.jetpack_views

import androidx.compose.Composable
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.Spacer
import androidx.ui.unit.dp
import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.jetcompose.components.MovieDelegate
import com.kinley.moviebrowsing.jetcompose.HStack

@Composable
fun HMovieListView(movieList: List<Movie>, delegate: MovieDelegate) {
    HStack {
        movieList.forEach { movie ->
            Spacer(modifier = LayoutSize(16.dp, 0.dp))
            MovieView(movie = movie, delegate = delegate)
        }

    }
}