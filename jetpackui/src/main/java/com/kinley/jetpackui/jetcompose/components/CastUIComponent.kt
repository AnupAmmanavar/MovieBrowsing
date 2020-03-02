package com.kinley.jetpackui.jetcompose.components

import com.kinley.data.models.Cast
import com.kinley.jetpackui.jetcompose.jetpack_views.ComposableView
import com.kinley.jetpackui.jetcompose.jetpack_views.HCastView

class CastUIComponent(
    private val castList: List<Cast>
) : UIComponent<CastDelegate> {

    override fun composableView(delegate: CastDelegate): ComposableView = {
        HCastView(castList = castList, delegate = delegate)
    }

}

interface CastDelegate : UIDelegate {
    fun onCastClick(cast: Cast)
}