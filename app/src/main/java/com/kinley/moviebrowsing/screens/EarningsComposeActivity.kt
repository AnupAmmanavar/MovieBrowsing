@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.moviebrowsing.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import com.kinley.jetpackui.jetcompose.VStack
import com.kinley.jetpackui.jetcompose.components.*
import com.kinley.jetpackui.jetcompose.jetpack_views.render
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi

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

  override fun onWeekSelected(week: Week) {
    vm.onWeekSelected(week)
  }
}

@ExperimentalCoroutinesApi
class EarningsViewModel : WeekVMDelegate, DayVMDelegate, CoroutineScope by CoroutineScope(Main) {

  var weekUXComponent: WeekUXComponent

  var dayUXComponent: DayUXComponent

  private var weekUIModel: WeekUIModel = WeekUIModel(arrayListOf("0", "1", "2", "3", "4", "5", "6", "7").map { Week(it) }, null)
  private var dayUIModel: DayUIModel = DayUIModel(arrayListOf("1", "2", "3", "4", "5", "6", "7").map { Day(it) }, null)

  init {
    weekUXComponent = WeekUXComponent(this, weekUIModel)
    dayUXComponent = DayUXComponent(this, dayUIModel)
  }

  override fun onWeekSelected(week: Week) {
    // Resetting the day
    dayUIModel.reset()
    weekUIModel.selectedWeek = week
  }

  override fun onDaySelected(day: Day) {
    dayUIModel.selectedDay = day
  }

}