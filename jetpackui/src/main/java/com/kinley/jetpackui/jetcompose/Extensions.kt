package com.kinley.jetpackui.jetcompose


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.*
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Image
import androidx.ui.graphics.ImageConfig
import androidx.ui.graphics.colorspace.ColorSpaces
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Row
import androidx.ui.unit.dp
import coil.Coil
import coil.api.get
import coil.api.newGetBuilder
import coil.request.GetRequest

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

@Model
class ImageState {
    var image: Image = Image(64, 64)
}

class RemoteImage(private val bitmap: Bitmap) : Image {
    override val width = bitmap.width
    override val height = bitmap.height
    override val config = ImageConfig.Argb8888
    override val colorSpace = ColorSpaces.Srgb
    override val hasAlpha = bitmap.hasAlpha()
    override val nativeImage = bitmap
    override fun prepareToDraw() = bitmap.prepareToDraw()
}

suspend fun loadImage(imageUrl: String): Drawable {
    return Coil.get("https://image.tmdb.org/t/p/w300/${imageUrl}") {}
}