package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import com.kinley.moviebrowsing.components.CastDelegate
import com.kinley.moviebrowsing.components.UIDelegate

interface JetView<Delegate : UIDelegate> {
    @Composable
    fun composableView(delegate: Delegate): ComposableView
}