package com.kinley.moviebrowsing.features

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kinley.moviebrowsing.CastViewBindingModel_
import com.kinley.moviebrowsing.PersonCastCellBindingModel_
import com.kinley.moviebrowsing.PersonViewBindingModel_

import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.features.moviedetail.MovieDetailPageDirections
import kotlinx.android.synthetic.main.persone_page_fragment.*

class PersonPage : Fragment() {

    private val viewModel: PersonPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.persone_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = PersonPageArgs.fromBundle(arguments!!)

        viewModel.load(args.id)

        epoxy.layoutManager = GridLayoutManager(context,  3)

        epoxy.withModels {
            val uiModel = viewModel.pageData.value

            if (uiModel?.person != null) {
                val person = uiModel.person
                PersonViewBindingModel_()
                    .id(person.id)
                    .spanSizeOverride { totalSpanCount, position, itemCount ->
                        totalSpanCount
                    }
                    .person(person)
                    .addTo(this)
            }

            val castList = uiModel?.castList ?: arrayListOf()
            castList.forEach { cast ->
                PersonCastCellBindingModel_()
                    .id(cast.id)
                    .cast(cast)
                    .spanSizeOverride { totalSpanCount, position, itemCount ->
                        totalSpanCount / 2
                    }
                    .onClick { _ ->
                        findNavController().navigate(MovieDetailPageDirections.actionMovieDetailPageToPersonPage(cast.id as Long))
                    }
                    .addTo(this)
            }


        }

        viewModel.pageData.observe(viewLifecycleOwner, Observer {
            epoxy.requestModelBuild()
        })
    }

}
