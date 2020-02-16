package com.kinley.moviebrowsing.components

import com.airbnb.epoxy.EpoxyModel

interface UIComponent<Delegate: UIDelegate> {
    fun render(delegate: Delegate): List<EpoxyModel<*>>
}