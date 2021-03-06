package com.kinley.jetpackui.jetcompose.jetpack_views

import androidx.compose.Composable
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutSize
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Card
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.dp
import com.kinley.data.models.Movie
import com.kinley.jetpackui.jetcompose.ImageState
import com.kinley.jetpackui.jetcompose.RemoteImage
import com.kinley.jetpackui.jetcompose.components.MovieDelegate
import com.kinley.jetpackui.jetcompose.loadImage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun MovieView(movie: Movie, delegate: MovieDelegate) {

    val state = ImageState()

    GlobalScope.launch {
        val drawable = loadImage(movie.poster_path ?: "")
        MainScope().launch {
            state.image = RemoteImage(drawable.toBitmap())
        }
    }


    val typography = MaterialTheme.typography()
    Clickable(onClick = {
        delegate.onMovieClick(movie)
    }) {

        Card(
            shape = RoundedCornerShape(
                14.dp
            )
        ) {

            Container(
                modifier = LayoutSize(
                    160.dp,
                    200.dp
                )
            ) {

                Column(
                    modifier = LayoutPadding(5.dp)
                ) {

                    Container(width = 160.dp, height = 160.dp) {
                        DrawImage(image = state.image)
                    }

                    Text(text = movie.title, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}