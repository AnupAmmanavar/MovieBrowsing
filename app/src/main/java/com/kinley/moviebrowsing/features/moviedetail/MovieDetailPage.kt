package com.kinley.moviebrowsing.features.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.kinley.moviebrowsing.R
import kotlinx.android.synthetic.main.movie_detail_page_fragment.*

class MovieDetailPage : Fragment() {


    private val viewModel: MovieDetailPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_detail_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = MovieDetailPageArgs.fromBundle(arguments!!)
        tv_movie_id.text = "${args.movieId}"



    }

}
