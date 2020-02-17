package com.kinley.moviebrowsing.jetcompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Card
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.features.home.HomePageUIModel
import com.kinley.moviebrowsing.features.home.HomePageViewModel

class JetPackComposeActivity : AppCompatActivity() {


  private val vm: HomePageViewModel by viewModels()

  private var pageUIModel: HomePageUIModel = HomePageUIModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    lifecycle.addObserver(vm)

    vm.pageData.observe(this, Observer {
      pageUIModel.copy(
        movieListUIComponents = it.movieListUIComponents
      )
    })

    setContent {
      HomePage(vm.pageData)
    }
  }
}

@Composable
fun HomePage(homePageUIModelLiveData: LiveData<HomePageUIModel>) {

  val homePageUIModel = observe(data = homePageUIModelLiveData)
  Column {
    Text(text = "Size ${homePageUIModel?.movieListUIComponents?.size}")

    VerticalScroller {
      Column(modifier = LayoutPadding(4.dp)) {

        homePageUIModel?.movieListUIComponents?.forEach { movieListUIComponent ->

          HorizontalScroller {
            Row(modifier = LayoutPadding(4.dp)) {
              movieListUIComponent.data.forEach { movie ->
                MovieView(movie = movie)
              }
            }
          }
        }
      }
    }


  }


}

@Composable
fun MovieView(movie: Movie) {
  val typography = MaterialTheme.typography()
  Card(shape = RoundedCornerShape(4.dp)) {

    Container(modifier = LayoutSize(280.dp, 280.dp)) {

      Column(modifier = LayoutPadding(5.dp)) {
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

@Preview
@Composable
fun DefaultPreview() {
  MaterialTheme {
    MovieView(movie = Movie.testData)
  }
}