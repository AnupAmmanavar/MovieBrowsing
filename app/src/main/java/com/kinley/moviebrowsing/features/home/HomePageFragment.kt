package com.kinley.moviebrowsing.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.airbnb.epoxy.*
import com.kinley.moviebrowsing.MovieCellBindingModel_
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.epoxy.withModelsFrom
import kotlinx.android.synthetic.main.fragment_home_page.*


class HomePageFragment : Fragment() {

    private val vm: HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        vm.load()

        epoxy_rv.withModels {
            val contents = vm.appData.value ?: arrayListOf()

            contents.forEachIndexed { index, movies ->

                carousel {
                    id(index)
                    numViewsToShowOnScreen(2.3f)
                    withModelsFrom(movies) { movie ->
                        MovieCellBindingModel_()
                            .id(movie.id)
                            .movie(movie)
                    }
                }
            }

        }

        vm.appData.observe(viewLifecycleOwner, Observer {
            epoxy_rv.requestModelBuild()
        })

    }
}
