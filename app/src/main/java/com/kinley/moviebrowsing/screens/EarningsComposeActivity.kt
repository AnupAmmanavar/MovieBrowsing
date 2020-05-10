@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.moviebrowsing.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import com.kinley.data.models.earnings.DailyReport
import com.kinley.data.models.earnings.WeeklyEarningReport
import com.kinley.data.repository.OrderEarningRepositoryImpl
import com.kinley.data.repository.OrderEarningsRepository
import com.kinley.jetpackui.jetcompose.VStack
import com.kinley.jetpackui.jetcompose.components.*
import com.kinley.jetpackui.jetcompose.jetpack_views.render
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

class EarningsComposeActivity : AppCompatActivity(), WeekUIDelegate {

  private val vm = EarningsViewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        VStack {
          vm.weekUXComponent.composableView(this).render()

          vm.dayUXComponent.composableView(this).render()
        }
      }
    }
  }

  override fun onWeekSelected(week: WeeklyEarningReport) {
    vm.onWeekSelected(week)
  }
}

@ExperimentalCoroutinesApi
class EarningsViewModel : WeekVMDelegate, DayVMDelegate, CoroutineScope by CoroutineScope(Main) {

  var weekUXComponent: WeekUXComponent

  var dayUXComponent: DayUXComponent

  private val earningsRepository: OrderEarningsRepository = OrderEarningRepositoryImpl()

  private var weekUIModel: WeekUIModel = WeekUIModel(emptyList(), null)
  private var dayUIModel: DayUIModel = DayUIModel(emptyList(), null)

  init {

    val reports = earningsRepository.getWeeklyReportList()
    weekUIModel = WeekUIModel(reports, null)

    weekUXComponent = WeekUXComponent(this@EarningsViewModel, weekUIModel)
    dayUXComponent = DayUXComponent(this@EarningsViewModel, dayUIModel)


  }

  override fun onWeekSelected(week: WeeklyEarningReport) {
    // Resetting the day
    dayUIModel.reset()
    dayUIModel.days = week.dailyReports
    weekUIModel.selectedWeek = week
  }

  override fun onDaySelected(day: DailyReport) {
    dayUIModel.selectedDay = day
  }

}