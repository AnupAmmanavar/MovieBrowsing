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
import androidx.ui.text.font.FontStyle
import androidx.ui.text.style.TextOverflow
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import com.kinley.data.models.Crew
import com.kinley.jetpackui.R
import com.kinley.jetpackui.jetcompose.components.CrewDelegate
import com.kinley.jetpackui.jetcompose.HStack
import com.kinley.jetpackui.jetcompose.ImageState
import com.kinley.jetpackui.jetcompose.RemoteImage
import com.kinley.jetpackui.jetcompose.loadImage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun CrewView(crew: Crew, delegate: CrewDelegate) {

    val state = ImageState()

    GlobalScope.launch {
        val drawable = loadImage(crew.profile_path ?: "")
        MainScope().launch {
            state.image = RemoteImage(drawable.toBitmap())
        }
    }

    val layoutWidth = 100.dp

    Clickable(onClick = { delegate.onCrewClick(crew) }) {
        Container(padding = EdgeInsets(8.dp)) {
            Card(shape = RoundedCornerShape(10), borderWidth = 8.dp) {
                Column {
                    Container(width = layoutWidth, height = layoutWidth) {
                        Clip(shape = CircleShape) {
                            DrawImage(image = state.image)
                        }
                    }
                    Text(text = crew.name, style = TextStyle(color = Color(R.color.green)), maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = LayoutWidth(layoutWidth))
                }
            }
        }
    }
}

@Composable
fun HCrewView(crewList: List<Crew>, delegate: CrewDelegate) {

    Card(borderWidth = 8.dp, contentColor = Color.Cyan) {
        Column {
            Text(text = "Crew", modifier = LayoutPadding(8.dp), style = TextStyle(Color.Black, fontStyle = FontStyle.Italic, fontSize = TextUnit(12)))
            HStack {
                crewList.forEach { crew ->
                    CrewView(crew = crew, delegate = delegate)
                }
            }
        }
    }
}