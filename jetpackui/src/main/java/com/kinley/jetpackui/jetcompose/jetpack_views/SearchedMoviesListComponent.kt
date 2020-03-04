package com.kinley.jetpackui.jetcompose.jetpack_views

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.layout.Column
import com.kinley.data.models.Movie
import com.kinley.jetpackui.jetcompose.components.MovieDelegate
import com.kinley.jetpackui.jetcompose.components.UIComponent

class SearchedMoviesListComponent(
    private val header: String,
    val movies: List<Movie>
) : UIComponent<MovieDelegate> {

    override fun composableView(delegate: MovieDelegate): ComposableView {
        return {
            Column {
                Text(text = header)
                SearchedMoviesView(movies = movies, movieDelegate = delegate)
            }
        }
    }

}

@Composable
fun SearchedMoviesView(movies: List<Movie>, movieDelegate: MovieDelegate) {

    movies.forEach { movie ->
        MovieDetailView(movie = movie, delegate = movieDelegate)
    }
}