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
import com.kinley.data.models.Cast
import com.kinley.moviebrowsing.R
import com.kinley.moviebrowsing.components.CastDelegate
import com.kinley.moviebrowsing.components.CastUIComponent
import com.kinley.moviebrowsing.jetcompose.screens.HStack

@Composable
fun CastView(cast: Cast, delegate: CastDelegate) {
    Container(modifier = LayoutPadding(8.dp), padding = EdgeInsets(8.dp)) {
        Card(shape = RoundedCornerShape(10), borderWidth = 8.dp) {

            Clickable(onClick = {
                delegate.onCastClick(cast)
            }) {
                Text(text = cast.name, style = TextStyle(color = Color(R.color.yellow)))
            }
        }
    }
}

@Composable
fun HCastView(castListUIComponent: CastUIComponent, delegate: CastDelegate) {
    HStack {
        castListUIComponent.data.forEach { cast ->
            CastView(cast = cast, delegate = delegate)
        }
    }

}
