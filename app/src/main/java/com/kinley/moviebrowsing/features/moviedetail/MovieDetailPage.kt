package com.kinley.moviebrowsing.features.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.carousel
import com.kinley.moviebrowsing.CastViewBindingModel_
import com.kinley.moviebrowsing.CrewCellBindingModel_
import com.kinley.moviebrowsing.MovieDetailBindingModel_
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.databinding.MovieDetailPageFragmentBinding
import com.kinley.moviebrowsing.epoxy.withModelsFrom
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

            val movie = viewModel.movie.value

            if (movie != null) {
                MovieDetailBindingModel_()
                    .id(movie.id)
                    .movie(movie)
                    .addTo(this)
            }

            val castMembers = viewModel.cast.value ?: arrayListOf()
            carousel {
                id("castMembers")
                numViewsToShowOnScreen(2.8f)
                withModelsFrom(castMembers) { cast ->
                    CastViewBindingModel_()
                        .id(cast.id)
                        .cast(cast)
                }
            }

            val crewMembers = viewModel.crew.value ?: arrayListOf()

            carousel {
                id("crew_members")
                    .numViewsToShowOnScreen(2.8f)
                    .withModelsFrom(crewMembers) { crew ->
                        CrewCellBindingModel_()
                            .id(crew.id)
                            .crew(crew)
                    }
            }
        }
        viewModel.movie.observe(viewLifecycleOwner, Observer { epoxy.requestModelBuild() })
        viewModel.crew.observe(viewLifecycleOwner, Observer { epoxy.requestModelBuild() })
        viewModel.cast.observe(viewLifecycleOwner, Observer { epoxy.requestModelBuild() })


    }

}
