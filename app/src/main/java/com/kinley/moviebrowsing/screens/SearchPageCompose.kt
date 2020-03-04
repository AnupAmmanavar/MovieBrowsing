package com.kinley.moviebrowsing.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Context
import androidx.lifecycle.LiveData
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.layout.Column
import androidx.ui.material.MaterialTheme
import com.kinley.data.models.Movie
import com.kinley.jetpackui.jetcompose.VStack
import com.kinley.jetpackui.jetcompose.components.MovieDelegate
import com.kinley.jetpackui.jetcompose.jetpack_views.render
import com.kinley.jetpackui.jetcompose.observe
import com.kinley.moviebrowsing.features.search.SearchPageUIModel
import com.kinley.moviebrowsing.features.search.SearchPageViewModel

class SearchPageCompose : AppCompatActivity(), MovieDelegate {

    companion object {
        fun getIntent(context: Context) = Intent(context, SearchPageCompose::class.java)
    }

    val vm: SearchPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                SearchPageView(pageData = vm.pageData, movieDelegate = this)
            }
        }
    }

    override fun onMovieClick(movie: Movie) {
        startActivity(MovieDetailActivity.getIntent(context = this, movieId = movie.id))
    }
}

@Composable
fun SearchPageView(pageData: LiveData<SearchPageUIModel>, movieDelegate: MovieDelegate) {

    val uiModel = observe(data = pageData)

    Column {
        uiModel?.inputViewComponent?.composableView(movieDelegate).render()

        uiModel?.searchedMoviesListComponent?.let {

            AdapterList(data = arrayListOf(it)) {
                it.composableView(delegate = movieDelegate).render()
            }
        }
    }
}