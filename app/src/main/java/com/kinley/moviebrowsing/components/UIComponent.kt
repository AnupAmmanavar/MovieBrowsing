package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel

interface UIComponent<DataHolder ,Delegate: UIDelegate> {
    open val data: DataHolder
    fun render(delegate: Delegate): List<EpoxyModel<*>>
}