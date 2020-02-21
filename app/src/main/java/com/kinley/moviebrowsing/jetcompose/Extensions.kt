package com.kinley.moviebrowsing.jetcompose


import androidx.compose.*
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Image
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Row
import androidx.ui.unit.dp
import coil.Coil
import coil.api.newGetBuilder
import coil.request.GetRequest
import com.kinley.moviebrowsing.extensions.RemoteImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

/**
 * Note : This has been picked up from https://tech.instacart.com/exploring-images-in-jetpack-compose-c8ba87089c92
 */

@Composable
fun image(data: Any) : Image? {
    // Positionally memoize the request creation so
    // it will only be recreated if data changes.
    val request = remember(data) {
        Coil.loader().newGetBuilder().data("https://image.tmdb.org/t/p/w300/$data").build()
    }
    return image(request)
}

@Composable
fun image(request: GetRequest) : Image? {
    val image = state<Image?> { null }

    // Execute the following code whenever the request changes.
    onCommit(request) {
        val job = CoroutineScope(Dispatchers.Main.immediate).launch {
            // Start loading the image and await the result.
            val drawable = Coil.loader().get(request)
            image.value = RemoteImage(drawable.toBitmap())
        }

        // Cancel the request if the input to onCommit changes or
        // the Composition is removed from the composition tree.
        onDispose { job.cancel() }
    }

    // Emit a null Image to start with.
    return image.value
}