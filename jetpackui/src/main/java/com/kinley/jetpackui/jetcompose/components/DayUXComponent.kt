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
import com.kinley.data.models.earnings.DailyReport
import com.kinley.jetpackui.R
import com.kinley.jetpackui.jetcompose.HStack
import com.kinley.jetpackui.jetcompose.VStack
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
  fun onDaySelected(day: DailyReport)
}


@Model
data class DayUIModel(
  var dailyReport: List<DailyReport>,
  var selectedDailyReport: DailyReport?
) {
  fun reset() {
    selectedDailyReport = null
  }
}

@Composable
fun HDayListView(vmDelegate: DayVMDelegate, data: DayUIModel) {
  HStack {
    data.dailyReport.forEach {
      DayView(vmDelegate = vmDelegate, dailyReport = it, isSelected = it.id == data.selectedDailyReport?.id)
    }
  }
}

@Composable
fun DayView(vmDelegate: DayVMDelegate, dailyReport: DailyReport, isSelected: Boolean) {
  val color = Color(if (isSelected) R.color.golden else R.color.yellow)
  Clickable(onClick = {
    vmDelegate.onDaySelected(dailyReport)
  }) {
    Container(padding = EdgeInsets(8.dp)) {
      Card(shape = RoundedCornerShape(10), color = color) {

        VStack {
          if (isSelected)
            Text(text = dailyReport.id, modifier = LayoutPadding(8.dp))
          else
            Text(text = dailyReport.id, modifier = LayoutPadding(8.dp))
          Text(text = "Earning ${dailyReport.earnings}", modifier = LayoutPadding(8.dp))
        }


      }
    }
  }
}