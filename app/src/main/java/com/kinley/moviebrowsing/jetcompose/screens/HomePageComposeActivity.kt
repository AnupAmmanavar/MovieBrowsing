package com.kinley.moviebrowsing.jetcompose.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import com.kinley.data.models.Movie
import com.kinley.moviebrowsing.components.MovieDelegate
import com.kinley.moviebrowsing.features.home.HomePageUIModel
import com.kinley.moviebrowsing.features.home.HomePageViewModel
import com.kinley.moviebrowsing.jetcompose.VStack
import com.kinley.moviebrowsing.jetcompose.observe
import com.kinley.moviebrowsing.jetcompose.uicomponents.render

class HomePageComposeActivity : AppCompatActivity(), MovieDelegate {


    private val vm: HomePageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(vm)

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

    Column {
        Column {
            homePageUIModel?.inputViewComponent?.composableView(movieDelegate).render()
            homePageUIModel?.searchButtonComponent?.composableView(movieDelegate).render()
        }
        VStack {
            homePageUIModel?.searchedMovies?.composableView(movieDelegate).render()
            homePageUIModel?.movieListUIComponents?.forEach { movieListUIComponent ->
                movieListUIComponent.composableView(movieDelegate).render()
            }
        }
    }


}