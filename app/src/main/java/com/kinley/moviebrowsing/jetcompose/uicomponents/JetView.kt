package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import com.kinley.moviebrowsing.components.CastDelegate

interface JetView {
    @Composable
    fun composableView(delegate: CastDelegate): ComposableView
}