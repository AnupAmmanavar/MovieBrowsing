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
import com.kinley.data.models.earnings.WeeklyEarningReport
import com.kinley.jetpackui.R
import com.kinley.jetpackui.jetcompose.HStack
import com.kinley.jetpackui.jetcompose.VStack
import com.kinley.jetpackui.jetcompose.jetpack_views.ComposableView


class WeekUXComponent(
  private val vmDelegate: WeekVMDelegate,
  dataStream: WeekUIModel
) : UXComponent<UIDelegate, WeekVMDelegate, WeekUIModel>(vmDelegate, dataStream) {

  override fun composableView(delegate: UIDelegate): ComposableView = {
    HWeekListView(ui = delegate, vm = vmDelegate, data = data)
  }
}

@Composable
fun HWeekListView(ui: UIDelegate, vm: WeekVMDelegate, data: WeekUIModel) {
  HStack {
    data.weeklyEarningReports.forEach { weeklyEarningReport ->
      WeekView(ui = ui, vm = vm, weeklyEarningReport = weeklyEarningReport, isSelected = weeklyEarningReport.equals(data.selectedWeeklyEarningReport))
    }
  }
}

@Composable
fun WeekView(ui: UIDelegate, vm: WeekVMDelegate, weeklyEarningReport: WeeklyEarningReport, isSelected: Boolean) {

  val color = Color(if (isSelected) R.color.golden else R.color.yellow)
  Container(padding = EdgeInsets(8.dp)) {
    Card(shape = RoundedCornerShape(10), color = color) {

      Clickable(onClick = {
        vm.onWeekSelected(weeklyEarningReport)
      }) {
        VStack {
          Text(text = "${weeklyEarningReport.startDate} - ${weeklyEarningReport.endDate}", modifier = LayoutPadding(8.dp))
          Text(text = "Earning ${weeklyEarningReport.earnings}", modifier = LayoutPadding(8.dp))
        }
      }
    }
  }

}


interface WeekUIDelegate : UIDelegate {
  fun onWeekSelected(week: WeeklyEarningReport)
}

interface WeekVMDelegate : VMDelegate {
  fun onWeekSelected(week: WeeklyEarningReport)
}

@Model
data class WeekUIModel(
  val weeklyEarningReports: List<WeeklyEarningReport>,
  var selectedWeeklyEarningReport: WeeklyEarningReport?
)