package com.kinley.moviebrowsing.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.carousel
import com.kinley.moviebrowsing.MovieCellBindingModel_
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.epoxy.withModelsFrom
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment() {

    private val vm: HomePageViewModel by viewModels()

    private val navigator: HomePageNavigator by lazy {
        HomePageNavigatorImpl(navController = findNavController())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lifecycle.addObserver(vm)

        epoxy_rv.withModels {
            val contents = vm.pageData.value ?: arrayListOf()

            contents.forEachIndexed { index, movies ->

                carousel {
                    id(index)
                    numViewsToShowOnScreen(2.3f)
                    withModelsFrom(movies) { movie ->
                        MovieCellBindingModel_()
                            .id(movie.id)
                            .movie(movie)
                            .onClick { _ ->
                                navigator.toMovieDetailPage(movie.id)
                            }
                    }
                }
            }

        }

        vm.pageData.observe(viewLifecycleOwner, Observer {
            epoxy_rv.requestModelBuild()
        })

    }
}

