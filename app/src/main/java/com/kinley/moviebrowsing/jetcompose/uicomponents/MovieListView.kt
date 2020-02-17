package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Row
import androidx.ui.unit.dp
import com.kinley.moviebrowsing.components.MovieListUIComponent

@Composable
fun MovieListView(movieListUIComponent: MovieListUIComponent) {
    HorizontalScroller {
        Row(
            modifier = LayoutPadding(
                4.dp
            )
        ) {
            movieListUIComponent.data.forEach { movie ->
                MovieView(movie = movie)
            }
        }
    }
}