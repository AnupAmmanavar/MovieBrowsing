package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable

typealias ComposableView = @Composable() () -> Unit

/**
 * This provides a clear readable name
 */
@Composable
fun ComposableView?.render() {
    this?.invoke()
}
