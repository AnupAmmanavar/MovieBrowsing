package com.kinley.jetpackui.jetcompose.components

import androidx.compose.Composable
import com.kinley.jetpackui.jetcompose.jetpack_views.ComposableView

interface UIComponent<Delegate : UIDelegate> {
    @Composable
    fun composableView(delegate: Delegate): ComposableView
}

