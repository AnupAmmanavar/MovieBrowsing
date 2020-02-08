package com.kinley.moviebrowsing.features

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.kinley.moviebrowsing.R

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

        viewModel.pageData.observe(viewLifecycleOwner, Observer {
            Log.d("Response", it.toString())
        })
    }

}
