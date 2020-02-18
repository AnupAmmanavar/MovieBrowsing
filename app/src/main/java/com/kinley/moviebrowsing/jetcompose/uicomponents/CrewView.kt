package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.LayoutPadding
import androidx.ui.layout.Padding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Card
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp
import com.kinley.data.models.Crew
import com.kinley.moviebrowsing.R

@Composable
fun CrewView(crew: Crew) {
    Container(modifier = LayoutPadding(8.dp), padding = EdgeInsets(8.dp)) {
        Card(shape = RoundedCornerShape(10), borderWidth = 8.dp) {
            Text(text = crew.name, style = TextStyle(color = Color(R.color.golden)))
        }
    }
}

@Composable
@Preview
fun DefaultPreview() {
    MaterialTheme {
        CrewView(crew = Crew.testData)
    }
}