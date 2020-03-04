package com.kinley.jetpackui.jetcompose.jetpack_views

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.foundation.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.Button
import androidx.ui.material.surface.Surface
import androidx.ui.unit.dp
import com.kinley.jetpackui.jetcompose.components.UIComponent
import com.kinley.jetpackui.jetcompose.components.UIDelegate

class InputViewComponent(
    private val text: String?,
    private val UIEventDispatcher: UIEventDispatcher
) : UIComponent<UIDelegate> {

    override fun composableView(delegate: UIDelegate): ComposableView {
        return {
            InputView(text = text ?: "", UIEventDispatcher = UIEventDispatcher)
        }
    }

}

class SearchButtonComponent(
    private val buttonEventDispatcher: ButtonEventDispatcher
) : UIComponent<UIDelegate> {

    override fun composableView(delegate: UIDelegate): ComposableView {
        return {
            SearchButton(text = "Search", buttonEventDispatcher = buttonEventDispatcher)
        }
    }

}

@Composable
fun InputView(text: String, UIEventDispatcher: UIEventDispatcher) {
    val state = state { text }
    Surface(border = Border(1.dp, Color.Gray), shape = RoundedCornerShape(4), modifier = LayoutPadding(8.dp)) {
        TextField(
            value = state.value,
            onValueChange = {
                state.value = it
                UIEventDispatcher.onInputChange(it)
            },
            modifier = LayoutPadding(8.dp)
        )
    }
}

@Composable
fun SearchButton(text: String, buttonEventDispatcher: ButtonEventDispatcher) {
    Button(
        onClick = { buttonEventDispatcher.onSearchClicked() }
    ) {
        Text(text = text)
    }
}

interface ButtonEventDispatcher {
    fun onSearchClicked()
}

interface UIEventDispatcher {
    fun onInputChange(input: String)
}