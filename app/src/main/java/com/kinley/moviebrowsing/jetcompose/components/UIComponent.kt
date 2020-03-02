package com.kinley.moviebrowsing.jetcompose.components

import androidx.compose.Composable
import com.kinley.moviebrowsing.jetcompose.jetpack_views.ComposableView

interface UIComponent<Delegate : UIDelegate> {
    @Composable
    fun composableView(delegate: Delegate): ComposableView
}

