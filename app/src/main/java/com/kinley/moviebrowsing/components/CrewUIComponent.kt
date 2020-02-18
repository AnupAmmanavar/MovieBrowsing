package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel
import com.kinley.data.models.Crew
import com.kinley.moviebrowsing.CrewCellBindingModel_
import com.kinley.moviebrowsing.jetcompose.uicomponents.ComposableView
import com.kinley.moviebrowsing.jetcompose.uicomponents.HCrewView

class CrewUIComponent(
    override val data: List<Crew>,
    private val crewList: List<Crew> = data
) : UIComponent<List<Crew>, CrewDelegate> {

    override fun render(delegate: CrewDelegate): List<EpoxyModel<*>> {
        val crewModels: ArrayList<CrewCellBindingModel_> = arrayListOf()

        crewList.forEach { crew ->
            crewModels.add(
                CrewCellBindingModel_()
                    .id(crew.id)
                    .crew(crew)
                    .onClick { _ ->
                        delegate.onCrewClick(crew)
                    }
            )
        }
        return crewModels
    }

    override fun composableView(delegate: CrewDelegate): ComposableView = {
        HCrewView(crewListUIComponent = this, delegate = delegate)
    }
}

interface CrewDelegate : UIDelegate {
    fun onCrewClick(crew: Crew)
}