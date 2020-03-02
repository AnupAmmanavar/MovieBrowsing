package com.kinley.moviebrowsing.components

import com.kinley.data.models.Crew
import com.kinley.moviebrowsing.jetcompose.uicomponents.ComposableView
import com.kinley.moviebrowsing.jetcompose.uicomponents.HCrewView
import com.kinley.moviebrowsing.jetcompose.uicomponents.JetView

class CrewUIComponent(
    private val crewList: List<Crew>
) : JetView<CrewDelegate> {

    override fun composableView(delegate: CrewDelegate): ComposableView = {
        HCrewView(crewList = crewList, delegate = delegate)
    }
}

interface CrewDelegate : UIDelegate {
    fun onCrewClick(crew: Crew)
}