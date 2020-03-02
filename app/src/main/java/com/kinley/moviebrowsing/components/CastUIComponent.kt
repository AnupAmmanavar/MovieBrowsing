package com.kinley.moviebrowsing.components

import com.kinley.data.models.Cast
import com.kinley.moviebrowsing.jetcompose.uicomponents.ComposableView
import com.kinley.moviebrowsing.jetcompose.uicomponents.HCastView
import com.kinley.moviebrowsing.jetcompose.uicomponents.JetView

class CastUIComponent(
    private val castList: List<Cast>
) : JetView<CastDelegate> {

    override fun composableView(delegate: CastDelegate): ComposableView = {
        HCastView(castList = castList, delegate = delegate)
    }

}

interface CastDelegate : UIDelegate {
    fun onCastClick(cast: Cast)
}