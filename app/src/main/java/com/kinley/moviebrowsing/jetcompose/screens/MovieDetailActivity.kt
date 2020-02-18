package com.kinley.moviebrowsing.jetcompose.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.core.setContent
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Row
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import com.kinley.data.models.Cast
import com.kinley.data.models.Crew
import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.components.CastDelegate
import com.kinley.moviebrowsing.components.CrewDelegate
import com.kinley.moviebrowsing.components.MovieDelegate
import com.kinley.moviebrowsing.features.moviedetail.MovieDetailPageUiModel
import com.kinley.moviebrowsing.features.moviedetail.MovieDetailPageViewModel
import com.kinley.moviebrowsing.jetcompose.observe
import com.kinley.moviebrowsing.jetcompose.uicomponents.HMovieListView
import com.kinley.moviebrowsing.jetcompose.uicomponents.render

class MovieDetailActivity : AppCompatActivity(), CompositDelegate {

    private val vm: MovieDetailPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.load(3870L)
        setContent {
            MaterialTheme {
                MovieDetailPageView(vm.pageData, this)
            }
        }
    }

    override fun onCastClick(cast: Cast) {
        Toast.makeText(this, "Cast = ${cast.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onCrewClick(crew: Crew) {
        Toast.makeText(this, "Crew = ${crew.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onMovieClick(movie: Movie) {
        Toast.makeText(this, "Movie = ${movie.title}", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun MovieDetailPageView(data: LiveData<MovieDetailPageUiModel>, delegate: CompositDelegate) {
    val pageUiModel = observe(data = data)

    VStack {

        pageUiModel?.movieDetailUIComponent?.composableView(delegate).render()

        pageUiModel?.castUIComponent?.composableView(delegate).render()
        pageUiModel?.crewUIComponent?.composableView(delegate).render()

        pageUiModel?.similarMoviesListUIComponent?.composableView(delegate).render()
        pageUiModel?.recommendedMoviesListUIComponent?.composableView(delegate).render()
    }
}

interface CompositDelegate : CastDelegate, CrewDelegate, MovieDelegate

@Composable
fun VStack(child: @Composable() () -> Unit) {
    VerticalScroller { Column(modifier = LayoutPadding(4.dp)) { child() } }
}

@Composable
fun HStack(child: @Composable() () -> Unit) {
    HorizontalScroller { Row(modifier = LayoutPadding(4.dp)) { child() } }
}