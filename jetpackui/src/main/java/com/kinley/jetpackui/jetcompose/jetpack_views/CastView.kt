package com.kinley.jetpackui.jetcompose.jetpack_views

import androidx.compose.Composable
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.surface.Card
import androidx.ui.text.TextStyle
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.dp
import com.kinley.data.models.Cast
import com.kinley.jetpackui.R
import com.kinley.jetpackui.jetcompose.components.CastDelegate
import com.kinley.jetpackui.jetcompose.HStack
import com.kinley.jetpackui.jetcompose.ImageState
import com.kinley.jetpackui.jetcompose.RemoteImage
import com.kinley.jetpackui.jetcompose.loadImage
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

    Container(padding = EdgeInsets(8.dp)) {
        Card(shape = RoundedCornerShape(10), borderWidth = 8.dp) {

            Clickable(onClick = {
                delegate.onCastClick(cast)
            }) {
                Column {
                    Container(width = 100.dp, height = 100.dp) {
                        Clip(shape = CircleShape) {
                            DrawImage(image = state.image)
                        }
                    }
                    Text(text = cast.name, style = TextStyle(color = Color(R.color.yellow)), maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = LayoutWidth(100.dp))
                }
            }
        }
    }
}

@Composable
fun HCastView(castList: List<Cast>, delegate: CastDelegate) {
    Column {
        Text(text = "Cast", modifier = LayoutPadding(8.dp))
        HStack {
            castList.forEach { cast ->
                CastView(cast = cast, delegate = delegate)
            }
        }
    }

}
