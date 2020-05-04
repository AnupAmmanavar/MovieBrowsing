package com.kinley.jetpackui.jetcompose.components

import androidx.compose.Composable
import com.kinley.jetpackui.jetcompose.jetpack_views.ComposableView

interface UXComponentProtocol<Delegate : UIDelegate> {
    @Composable
    fun composableView(delegate: Delegate): ComposableView
}

abstract class UXComponent<UI : UIDelegate, VM : VMDelegate, Data>(
    private val vmDelegate: VM,
    private val dataStream: Data
) : UXComponentProtocol<UI> {

    var data: Data = dataStream

    private fun update(data: Data) {
        this.data = data
    }
}