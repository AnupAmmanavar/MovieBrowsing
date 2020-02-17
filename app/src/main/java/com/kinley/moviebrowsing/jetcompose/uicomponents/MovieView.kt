package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.LayoutSize
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Card
import androidx.ui.unit.dp
import com.kinley.data.models.Movie

@Composable
fun MovieView(movie: Movie) {
  val typography = MaterialTheme.typography()
    Card(
        shape = RoundedCornerShape(
            4.dp
        )
    ) {

        Container(
            modifier = LayoutSize(
                280.dp,
                280.dp
            )
        ) {

            Column(
                modifier = LayoutPadding(5.dp)
            ) {
                Text(
                    movie.title,
                    style = typography.h4
                )
                Text(movie.overview ?: "")
                Divider()
            }
        }
    }
}