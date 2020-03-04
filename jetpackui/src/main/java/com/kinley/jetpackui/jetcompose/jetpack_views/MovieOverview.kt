package com.kinley.jetpackui.jetcompose.jetpack_views

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Row
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.dp
import com.kinley.data.models.Movie
import com.kinley.jetpackui.R
import com.kinley.jetpackui.jetcompose.components.MovieDelegate
import com.kinley.jetpackui.jetcompose.image

@Composable
fun MovieDetailView(movie: Movie, delegate: MovieDelegate) {

  val image = image(data = movie.poster_path ?: "")
    ?: imageResource(id = R.drawable.image_missing_icon)


  val typography = MaterialTheme.typography()
  Clickable(onClick = { delegate.onMovieClick(movie = movie) }) {

    Row(modifier = LayoutPadding(8.dp)) {
      Container(width = 160.dp, height = 160.dp) {
        DrawImage(image = image)
      }

      Column {
        Text(movie.title, style = typography.h6)
        Divider()
        Text(movie.overview ?: "", maxLines = 5, overflow = TextOverflow.Ellipsis)
      }
    }
  }
}