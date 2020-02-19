package com.kinley.moviebrowsing.jetcompose.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.components.MovieDelegate
import com.kinley.moviebrowsing.features.home.HomePageUIModel
import com.kinley.moviebrowsing.features.home.HomePageViewModel
import com.kinley.moviebrowsing.jetcompose.observe
import com.kinley.moviebrowsing.jetcompose.uicomponents.render

class HomePageComposeActivity : AppCompatActivity(), MovieDelegate {


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
            HomePage(vm.pageData, this)
        }
    }

    override fun onMovieClick(movie: Movie) {
        startActivity(MovieDetailActivity.getIntent(this, movie.id))
    }
}

@Composable
fun HomePage(homePageUIModelLiveData: LiveData<HomePageUIModel>, movieDelegate: MovieDelegate) {

    val homePageUIModel =
        observe(data = homePageUIModelLiveData)

    VStack {
        homePageUIModel?.movieListUIComponents?.forEach { movieListUIComponent ->
            movieListUIComponent.composableView(movieDelegate).render()
        }
    }


}

/*
@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        MovieView(movie = Movie.testData)
    }
}*/
