package com.kinley.moviebrowsing.jetcompose.uicomponents

import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.TextField
import androidx.ui.material.Button
import androidx.ui.material.ContainedButtonStyle
import com.kinley.moviebrowsing.components.UIDelegate

class InputViewComponent(
    private val text: String?,
    private val UIEventDispatcher: UIEventDispatcher
) : JetView<UIDelegate> {

    override fun composableView(delegate: UIDelegate): ComposableView {
        return {
            InputView(text = text ?: "", UIEventDispatcher = UIEventDispatcher)
        }
    }

}

class SearchButtonComponent(
    private val buttonEventDispatcher: ButtonEventDispatcher
): JetView<UIDelegate> {

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
        text = text,
        onClick = { buttonEventDispatcher.onSearchClicked() },
        style  = ContainedButtonStyle()
    )
}

interface ButtonEventDispatcher {
    fun onSearchClicked()
}

interface UIEventDispatcher {
    fun onInputChange(input: String)
}