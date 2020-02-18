package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel
import com.kinley.moviebrowsing.jetcompose.uicomponents.JetView

interface UIComponent<DataHolder ,Delegate: UIDelegate> : JetView<Delegate> {
    open val data: DataHolder
    fun render(delegate: Delegate): List<EpoxyModel<*>>
}