package com.kinley.moviebrowsing.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.core.setContent
import com.kinley.data.models.Movie
import com.kinley.jetpackui.jetcompose.components.MovieDelegate
import com.kinley.moviebrowsing.features.home.HomePageUIModel
import com.kinley.moviebrowsing.features.home.HomePageViewModel
import com.kinley.jetpackui.jetcompose.VStack
import com.kinley.jetpackui.jetcompose.jetpack_views.render
import com.kinley.jetpackui.jetcompose.observe

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

    VStack {
        homePageUIModel?.searchedMovies?.composableView(movieDelegate).render()
        homePageUIModel?.movieListUIComponents?.forEach { movieListUIComponent ->
            movieListUIComponent.composableView(movieDelegate).render()
        }
    }


}