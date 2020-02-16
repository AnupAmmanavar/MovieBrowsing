package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel
import com.kinley.moviebrowsing.CrewCellBindingModel_
import com.kinley.moviebrowsing.models.Crew

class CrewUIComponent(
    val crewList: List<Crew>
) : UIComponent<CrewDelegate> {

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
}

interface CrewDelegate : UIDelegate {
    fun onCrewClick(crew: Crew)
}