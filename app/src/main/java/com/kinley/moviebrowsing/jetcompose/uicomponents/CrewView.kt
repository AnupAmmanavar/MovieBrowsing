package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import androidx.core.graphics.drawable.toBitmap
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.graphics.Image
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.surface.Card
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontStyle
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import com.kinley.data.models.Crew
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.components.CrewDelegate
import com.kinley.moviebrowsing.components.CrewUIComponent
import com.kinley.moviebrowsing.extensions.ImageState
import com.kinley.moviebrowsing.extensions.RemoteImage
import com.kinley.moviebrowsing.extensions.loadImage
import com.kinley.moviebrowsing.jetcompose.HStack
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

    Clickable(onClick = { delegate.onCrewClick(crew) }) {
        Container(modifier = LayoutPadding(8.dp), padding = EdgeInsets(8.dp)) {
            Card(shape = RoundedCornerShape(10), borderWidth = 8.dp) {
                Column {
                    Container(width = 80.dp, height = 80.dp) {
                        Clip(shape = CircleShape) {
                            DrawImage(image = state.image)
                        }
                    }
                    Text(text = crew.name, style = TextStyle(color = Color(R.color.green)))
                }
            }
        }
    }
}

@Composable
fun HCrewView(crewListUIComponent: CrewUIComponent, delegate: CrewDelegate) {

    Card(borderWidth = 8.dp, contentColor = Color.Cyan) {
        Column {
            Text(text = "Crew", modifier = LayoutPadding(8.dp), style = TextStyle(Color.Black, fontStyle = FontStyle.Italic, fontSize = TextUnit(12)))
            HStack {
                crewListUIComponent.data.forEach { crew ->
                    CrewView(crew = crew, delegate = delegate)
                }
            }
        }
    }
}