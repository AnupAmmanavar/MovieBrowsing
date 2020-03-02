package com.kinley.jetpackui.jetcompose.jetpack_views

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.LayoutSize
import androidx.ui.layout.Spacer
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import com.kinley.data.models.Movie
import com.kinley.jetpackui.jetcompose.components.MovieDelegate
import com.kinley.jetpackui.jetcompose.HStack
import com.kinley.jetpackui.jetcompose.VStack

@Composable
fun HMovieListView(header: String, movieList: List<Movie>, delegate: MovieDelegate) {
    VStack {

        val typography = MaterialTheme.typography()

        Text(text = header, style = typography.h6)

        HStack {
            movieList.forEach { movie ->
                MovieView(movie = movie, delegate = delegate)
                Spacer(modifier = LayoutSize(16.dp, 0.dp))
            }

        }
    }
}