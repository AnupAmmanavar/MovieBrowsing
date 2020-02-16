package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel
import com.kinley.moviebrowsing.CastViewBindingModel_
import com.kinley.moviebrowsing.models.Cast

class CastUIComponent(
    override val data: List<Cast>,
    private val castList: List<Cast> = data
) : UIComponent<List<Cast>, CastDelegate> {

    override fun render(delegate: CastDelegate): List<EpoxyModel<*>> {

        val castModels: ArrayList<CastViewBindingModel_> = arrayListOf()

        castList.forEach { cast ->
            castModels.add(
                CastViewBindingModel_()
                    .id(cast.id)
                    .cast(cast)
                    .onClick { _ ->
                        delegate.onCastClick(cast)
                    }
            )
        }
        return castModels
    }
}

interface CastDelegate : UIDelegate {
    fun onCastClick(cast: Cast)
}