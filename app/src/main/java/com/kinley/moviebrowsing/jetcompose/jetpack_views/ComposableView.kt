package com.kinley.moviebrowsing.jetcompose.jetpack_views

import androidx.compose.Composable

typealias ComposableView = @Composable() () -> Unit

/**
 * This provides a clear readable name
 */
@Composable
fun ComposableView?.render() {
    this?.invoke()
}
