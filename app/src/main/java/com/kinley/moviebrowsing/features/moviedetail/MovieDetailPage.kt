package com.kinley.moviebrowsing.features.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.databinding.MovieDetailPageFragmentBinding

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

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            v.movie = it
        })





    }

}
