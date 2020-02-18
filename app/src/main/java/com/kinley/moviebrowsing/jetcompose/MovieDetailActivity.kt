package com.kinley.moviebrowsing.jetcompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import com.kinley.moviebrowsing.features.moviedetail.MovieDetailPageUiModel
import com.kinley.moviebrowsing.features.moviedetail.MovieDetailPageViewModel
import com.kinley.moviebrowsing.jetcompose.uicomponents.HCastView
import com.kinley.moviebrowsing.jetcompose.uicomponents.HCrewView
import com.kinley.moviebrowsing.jetcompose.uicomponents.MovieListView

class MovieDetailActivity : AppCompatActivity() {

  private val vm: MovieDetailPageViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    vm.load(3870L)
    setContent {
      MaterialTheme {
        MovieDetailPageView(vm.pageData)
      }
    }
  }
}

@Composable
fun MovieDetailPageView(data: LiveData<MovieDetailPageUiModel>) {
  val pageUiModel = observe(data = data)

  VerticalScroller {
    Column(modifier = LayoutPadding(4.dp)) {

      pageUiModel?.castUIComponent?.let { HCastView(castListUIComponent = it) }
      pageUiModel?.crewUIComponent?.let { HCrewView(crewListUIComponent = it) }

      pageUiModel?.similarMoviesListUIComponent?.let { MovieListView(movieListUIComponent = it) }
      pageUiModel?.recommendedMoviesListUIComponent?.let { MovieListView(movieListUIComponent = it) }
    }
  }
}