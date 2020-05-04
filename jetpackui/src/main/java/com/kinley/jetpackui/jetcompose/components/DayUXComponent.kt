package com.kinley.jetpackui.jetcompose.components

import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.Text
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Container
import androidx.ui.layout.EdgeInsets
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.surface.Card
import androidx.ui.unit.dp
import com.kinley.jetpackui.R
import com.kinley.jetpackui.jetcompose.HStack
import com.kinley.jetpackui.jetcompose.jetpack_views.ComposableView

class DayUXComponent(
  private val vmDelegate: DayVMDelegate,
  private val uiModel: DayUIModel
) : UXComponent<UIDelegate, DayVMDelegate, DayUIModel>(vmDelegate, uiModel) {

  override fun composableView(delegate: UIDelegate): ComposableView = {
    HDayListView(vmDelegate = vmDelegate, data = uiModel)
  }

}

interface DayVMDelegate : VMDelegate {
  fun onDaySelected(day: Day)
}


data class Day(val day: String)

@Model
data class DayUIModel(var days: List<Day>, var selectedDay: Day?) {
  fun reset() {
    selectedDay = null
  }
}

@Composable
fun HDayListView(vmDelegate: DayVMDelegate, data: DayUIModel) {
  HStack {
    data.days.forEach {
      DayView(vmDelegate = vmDelegate, day = it, isSelected = it.day == data.selectedDay?.day)
    }
  }
}

@Composable
fun DayView(vmDelegate: DayVMDelegate, day: Day, isSelected: Boolean) {
  val color = Color(if (isSelected) R.color.golden else R.color.yellow)
  Clickable(onClick = {
    vmDelegate.onDaySelected(day)
  }) {
    Container(padding = EdgeInsets(8.dp)) {
      Card(shape = RoundedCornerShape(10), color = color) {


        if (isSelected)
          Text(text = "Selected ${day.day}", modifier = LayoutPadding(8.dp))
        else
          Text(text = day.day, modifier = LayoutPadding(8.dp))
      }
    }
  }
}