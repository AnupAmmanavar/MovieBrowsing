package com.kinley.moviebrowsing.extensions

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.compose.Model
import androidx.databinding.BindingAdapter
import androidx.ui.graphics.Image
import androidx.ui.graphics.ImageConfig
import androidx.ui.graphics.colorspace.ColorSpaces
import coil.Coil
import coil.api.get
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.kinley.moviebrowsing.R

@BindingAdapter("imageURL")
fun ImageView.loadImage(imageURL: String?) {
    val url = if (imageURL != null) "https://image.tmdb.org/t/p/w300/$imageURL" else null
    Glide.with(context)
        .load(url)
        .transform(RoundedCorners(16))
        .apply(
            RequestOptions().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )
        .into(this)
}
@BindingAdapter("circularImg")
fun ImageView.loadCircularImage(imageURL: String?) {
    val url = if (imageURL != null) "https://image.tmdb.org/t/p/w300/$imageURL" else null
    Glide.with(context)
        .load(url)
        .apply(
            RequestOptions().circleCrop().placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
        )
        .into(this)
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