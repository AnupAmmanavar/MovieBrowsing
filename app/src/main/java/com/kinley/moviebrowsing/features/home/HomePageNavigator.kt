package com.kinley.moviebrowsing.features.home

import androidx.navigation.NavController

interface HomePageNavigator {
    fun toMovieDetailPage(id: Long)
}

class HomePageNavigatorImpl(
    val navController: NavController
) : HomePageNavigator {

    override fun toMovieDetailPage(id: Long) {
        navController.navigate(HomePageFragmentDirections.actionHomePageFragmentToMovieDetailPage(id))
    }

}