package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.surface.Card
import androidx.ui.text.TextStyle
import androidx.ui.unit.dp
import com.kinley.data.models.Crew
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.components.CrewDelegate
import com.kinley.moviebrowsing.components.CrewUIComponent
import com.kinley.moviebrowsing.jetcompose.HStack

@Composable
fun CrewView(crew: Crew, delegate: CrewDelegate) {
    Clickable(onClick = { delegate.onCrewClick(crew) }) {
        Container(modifier = LayoutPadding(8.dp), padding = EdgeInsets(8.dp)) {
            Card(shape = RoundedCornerShape(10), borderWidth = 8.dp) {
                Text(text = crew.name, style = TextStyle(color = Color(R.color.green)))
            }
        }
    }
}

@Composable
fun HCrewView(crewListUIComponent: CrewUIComponent, delegate: CrewDelegate) {
    HStack {
        crewListUIComponent.data.forEach { crew ->
            CrewView(crew = crew, delegate = delegate)
        }
    }
}

//@Composable
//@Preview
//fun DefaultPreview() {
//    MaterialTheme {
//        CrewView(crew = Crew.testData, delegate = CrewDelegate)
//    }
//}