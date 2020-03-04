package com.kinley.moviebrowsing.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.lifecycle.LiveData
import androidx.ui.core.setContent
import androidx.ui.foundation.AdapterList
import androidx.ui.material.MaterialTheme
import com.kinley.data.models.Cast
import com.kinley.data.models.Crew
import com.kinley.data.models.Movie
import com.kinley.jetpackui.jetcompose.VStack
import com.kinley.jetpackui.jetcompose.components.CompositDelegate
import com.kinley.jetpackui.jetcompose.components.UIComponent
import com.kinley.jetpackui.jetcompose.components.UIDelegate
import com.kinley.jetpackui.jetcompose.jetpack_views.render
import com.kinley.jetpackui.jetcompose.observe
import com.kinley.moviebrowsing.features.moviedetail.MovieDetailPageUiModel
import com.kinley.moviebrowsing.features.moviedetail.MovieDetailPageViewModel

class MovieDetailActivity : AppCompatActivity(), CompositDelegate {


    companion object {

        private val MOVIE_ID_REC = "movie_id_rec"

        fun getIntent(context: Context, movieId: Long): Intent {
            return Intent(context, MovieDetailActivity::class.java)
                .also {
                    it.putExtra(MOVIE_ID_REC, movieId)
                }
        }
    }

    private val vm: MovieDetailPageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieId = intent.extras?.getLong(MOVIE_ID_REC) ?: throw IllegalArgumentException("Please pass `$MOVIE_ID_REC` parameter")
        vm.load(movieId)
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
        // Dont handle click here
    }
}

@Composable
fun MovieDetailPageView(data: LiveData<MovieDetailPageUiModel>, delegate: CompositDelegate) {
    val pageUiModel = observe(data = data)

    VStack {

        pageUiModel?.components()?.forEach {
            it.composableView(delegate = delegate).render()
        }
    }
}