package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel
import com.kinley.data.models.Cast
import com.kinley.moviebrowsing.CastViewBindingModel_
import com.kinley.moviebrowsing.jetcompose.uicomponents.ComposableView
import com.kinley.moviebrowsing.jetcompose.uicomponents.HCastView

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

    override fun composableView(delegate: CastDelegate): ComposableView = {
        HCastView(castListUIComponent = this, delegate = delegate)
    }

}

interface CastDelegate : UIDelegate {
    fun onCastClick(cast: Cast)
}