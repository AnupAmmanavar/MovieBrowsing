package com.kinley.jetpackui.jetcompose.jetpack_views

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Text
import androidx.ui.core.TextField
import androidx.ui.material.Button
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
    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
            UIEventDispatcher.onInputChange(it)
        }
    )
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