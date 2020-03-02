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