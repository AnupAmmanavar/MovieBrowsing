@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.moviebrowsing.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.kinley.jetpackui.jetcompose.components.*
import com.kinley.jetpackui.jetcompose.jetpack_views.render
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity(), WeekUIDelegate {

  private val vm = MainViewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        vm.component.composableView(this).render()
      }
    }
  }

  override fun onWeekSelected(identifier: String) {
    vm.onWeekSelected(identifier)
  }
}

@ExperimentalCoroutinesApi
class MainViewModel() : WeekVMDelegate, CoroutineScope by CoroutineScope(Main) {

  var component: WeekUXComponent

  private var uiModel: WeekUIModel = WeekUIModel(arrayListOf("0", "1", "2", "3", "4", "5", "6", "7"), "0")

  init {
    component = WeekUXComponent(this, uiModel)
  }

  override fun onWeekSelected(identifier: String) {
    uiModel.selectedWeek = identifier
  }

}

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
  MaterialTheme {
    Greeting("Android")
  }
}