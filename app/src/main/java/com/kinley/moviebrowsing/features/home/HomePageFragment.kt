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
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.components.MovieDelegate
import com.kinley.data.models.Movie
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePageFragment : Fragment(), MovieDelegate {

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
            val movieListUIComponents = vm.pageData.value?.movieListUIComponents ?: arrayListOf()

            movieListUIComponents.forEachIndexed { index, movieListUIComponent ->
                carousel {
                    id(index)
                    numViewsToShowOnScreen(2.3f)
                    models(movieListUIComponent.render(this@HomePageFragment))
                }
            }

        }

        vm.pageData.observe(viewLifecycleOwner, Observer {
            epoxy_rv.requestModelBuild()
        })

    }

    override fun onMovieClick(movie: Movie) {
        navigator.toMovieDetailPage(movie.id)
    }
}

