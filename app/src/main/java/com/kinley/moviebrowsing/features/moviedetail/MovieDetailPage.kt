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
import com.kinley.moviebrowsing.*
import com.kinley.moviebrowsing.databinding.MovieDetailPageFragmentBinding
import com.kinley.moviebrowsing.epoxy.withModelsFrom
import com.kinley.moviebrowsing.models.Movie
import kotlinx.android.synthetic.main.movie_detail_page_fragment.*

class MovieDetailPage : Fragment() {

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

            val movie = uiModel?.movie
            if (movie != null) {
                MovieDetailBindingModel_()
                    .id(movie.id)
                    .movie(movie)
                    .addTo(this)
            }

            val castMembers = uiModel?.castMembers ?: arrayListOf()
            carousel {
                id("castMembers")
                numViewsToShowOnScreen(2.8f)
                withModelsFrom(castMembers) { cast ->
                    CastViewBindingModel_()
                        .id(cast.id)
                        .cast(cast)
                        .onClick { _ ->
                            findNavController().navigate(MovieDetailPageDirections.actionMovieDetailPageToPersonPage(cast.id as Long))
                        }
                }
            }

            val crewMembers = uiModel?.crewMembers ?: arrayListOf()
            carousel {
                id("crew_members")
                    .numViewsToShowOnScreen(2.8f)
                    .withModelsFrom(crewMembers) { crew ->
                        CrewCellBindingModel_()
                            .id(crew.id)
                            .crew(crew)
                    }
            }

            renderMovies("similar_movies", uiModel?.similarMovies ?: arrayListOf(), this)
            renderMovies("recommended_movies", uiModel?.recommendedMovies ?: arrayListOf(), this)
        }
        viewModel.pageData.observe(viewLifecycleOwner, Observer { epoxy.requestModelBuild() })
    }

    private fun renderMovies(
        id: String,
        movies: List<Movie>,
        epoxyController: EpoxyController
    ) {
        epoxyController.carousel {
            id(id)
            numViewsToShowOnScreen(2.3f)
            withModelsFrom(movies) { movie ->
                MovieCellBindingModel_()
                    .id(movie.id)
                    .movie(movie)
                    .onClick { _ ->
                        findNavController().navigate(MovieDetailPageDirections.actionMovieDetailPageSelf(movie.id))
                    }
            }
        }
    }

}
