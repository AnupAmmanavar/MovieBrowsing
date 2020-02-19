package com.kinley.moviebrowsing.jetcompose


import androidx.compose.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Row
import androidx.ui.unit.dp

@Composable
fun <T> observe(data: LiveData<T>) : T? {
    val result = state { data.value }
    val observer = remember { Observer<T> { result.value = it } }

    onCommit(data) {
        data.observeForever(observer)
        onDispose { data.removeObserver(observer) }
    }

    return result.value
}

@Composable
fun VStack(child: @Composable() () -> Unit) {
    VerticalScroller { Column(modifier = LayoutPadding(4.dp)) { child() } }
}

@Composable
fun HStack(child: @Composable() () -> Unit) {
    HorizontalScroller { Row(modifier = LayoutPadding(4.dp)) { child() } }
}