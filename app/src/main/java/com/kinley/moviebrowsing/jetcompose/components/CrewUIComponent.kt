package com.kinley.moviebrowsing.jetcompose.components

import com.kinley.data.models.Crew
import com.kinley.moviebrowsing.jetcompose.jetpack_views.ComposableView
import com.kinley.moviebrowsing.jetcompose.jetpack_views.HCrewView

class CrewUIComponent(
    private val crewList: List<Crew>
) : UIComponent<CrewDelegate> {

    override fun composableView(delegate: CrewDelegate): ComposableView = {
        HCrewView(crewList = crewList, delegate = delegate)
    }
}

interface CrewDelegate : UIDelegate {
    fun onCrewClick(crew: Crew)
}