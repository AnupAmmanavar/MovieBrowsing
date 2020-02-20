package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.surface.Card
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import com.kinley.data.models.Cast
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.components.CastDelegate
import com.kinley.moviebrowsing.components.CastUIComponent
import com.kinley.moviebrowsing.extensions.ImageState
import com.kinley.moviebrowsing.extensions.RemoteImage
import com.kinley.moviebrowsing.extensions.loadImage
import com.kinley.moviebrowsing.jetcompose.HStack
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun CastView(cast: Cast, delegate: CastDelegate) {

    val state = ImageState()

    GlobalScope.launch {
        val drawable = loadImage(cast.profile_path ?: "")
        MainScope().launch {
            state.image = RemoteImage(drawable.toBitmap())
        }
    }

    Container(modifier = LayoutPadding(8.dp), padding = EdgeInsets(8.dp)) {
        Card(shape = RoundedCornerShape(10), borderWidth = 8.dp) {

            Clickable(onClick = {
                delegate.onCastClick(cast)
            }) {
                Column {
                    Container(width = 80.dp, height = 80.dp) {
                        DrawImage(image = state.image)
                    }
                    Text(text = cast.name, style = TextStyle(color = Color(R.color.yellow)))
                }
            }
        }
    }
}

@Composable
fun HCastView(castListUIComponent: CastUIComponent, delegate: CastDelegate) {
    Column {
        Text(text = "Cast")
        HStack {
            castListUIComponent.data.forEach { cast ->
                CastView(cast = cast, delegate = delegate)
            }
        }
    }

}
