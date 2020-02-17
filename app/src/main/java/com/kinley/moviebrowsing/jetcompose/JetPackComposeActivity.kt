package com.kinley.moviebrowsing.jetcompose

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.features.home.HomePageUIModel
import com.kinley.moviebrowsing.features.home.HomePageViewModel
import com.kinley.moviebrowsing.jetcompose.uicomponents.MovieListView
import com.kinley.moviebrowsing.jetcompose.uicomponents.MovieView

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
    VerticalScroller {
        Column(modifier = LayoutPadding(4.dp)) {
            homePageUIModel?.movieListUIComponents?.forEach { movieListUIComponent ->
                MovieListView(movieListUIComponent)
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