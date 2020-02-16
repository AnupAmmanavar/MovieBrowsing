package com.kinley.moviebrowsing.features.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.components.CastDelegate
import com.kinley.moviebrowsing.components.CrewDelegate
import com.kinley.moviebrowsing.components.MovieDelegate
import com.kinley.moviebrowsing.components.MovieListUIComponent
import com.kinley.moviebrowsing.databinding.MovieDetailPageFragmentBinding
import com.kinley.moviebrowsing.models.Cast
import com.kinley.moviebrowsing.models.Crew
import com.kinley.moviebrowsing.models.Movie
import kotlinx.android.synthetic.main.movie_detail_page_fragment.*

class MovieDetailPage : Fragment(), CrewDelegate, MovieDelegate, CastDelegate {

    private lateinit var v: MovieDetailPageFragmentBinding

    private val viewModel: MovieDetailPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = DataBindingUtil.inflate(inflater, R.layout.movie_detail_page_fragment, container, false)
        return v.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = MovieDetailPageArgs.fromBundle(arguments!!)
        viewModel.load(args.movieId)

        epoxy.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        epoxy.withModels {

            val uiModel = viewModel.pageData.value

            val movieDetailUIComponent = uiModel?.movieDetailUIComponent
            movieDetailUIComponent?.render(this@MovieDetailPage)?.forEach {
                it.addTo(this)
            }

            val castUIComponent =
                uiModel?.castUIComponent?.render(this@MovieDetailPage) ?: arrayListOf()
            carousel {
                id("castMembers")
                numViewsToShowOnScreen(2.8f)
                models(castUIComponent)
            }

            val crewUIComponent =
                uiModel?.crewUIComponent?.render(this@MovieDetailPage) ?: arrayListOf()
            carousel {
                id("crew_members")
                    .numViewsToShowOnScreen(2.8f)
                    .models(crewUIComponent)
            }

            renderMovies("similar_movies", uiModel?.similarMoviesListUIComponent, this)
            renderMovies("recommended_movies", uiModel?.recommendedMoviesListUIComponent, this)
        }
        viewModel.pageData.observe(viewLifecycleOwner, Observer { epoxy.requestModelBuild() })
    }

    private fun renderMovies(
        id: String,
        movieListUIComponent: MovieListUIComponent?,
        epoxyController: EpoxyController
    ) {
        val movieModels = movieListUIComponent?.render(this@MovieDetailPage) ?: arrayListOf()
        epoxyController.carousel {
            id(id)
            numViewsToShowOnScreen(2.3f)
            models(movieModels)
        }
    }

    override fun onCrewClick(crew: Crew) {
        findNavController().navigate(MovieDetailPageDirections.actionMovieDetailPageToPersonPage(crew.id))
    }

    override fun onCastClick(cast: Cast) {
        findNavController().navigate(MovieDetailPageDirections.actionMovieDetailPageToPersonPage(cast.id))
    }

    override fun onMovieClick(movie: Movie) {
        findNavController().navigate(MovieDetailPageDirections.actionMovieDetailPageSelf(movie.id))
    }

}
