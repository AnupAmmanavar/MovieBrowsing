package com.kinley.moviebrowsing.screens

import com.kinley.data.models.earnings.DailyReport
import com.kinley.data.models.earnings.WeeklyEarningReport
import com.kinley.jetpackui.jetcompose.components.DayUIModel
import com.kinley.jetpackui.jetcompose.components.EarningUIModel
import com.kinley.jetpackui.jetcompose.components.WeekUIModel

data class EarningPageUIModel(
  var reports: List<WeeklyEarningReport>
) {
  var weekUIModel: WeekUIModel = WeekUIModel(reports, null)
  var dayUIModel: DayUIModel = DayUIModel(emptyList(), null)
  var earningsUIModel = EarningUIModel(arrayListOf())

  fun updateWeek(weeklyEarningReport: WeeklyEarningReport) {

    with(earningsUIModel) {
      earnings = emptyList()
    }

    with(dayUIModel) {
      resetSelectedDay()
      dailyReport = weeklyEarningReport.dailyReports
    }

    with(weekUIModel) {
      selectedWeeklyEarningReport = weeklyEarningReport
    }

  }

  fun updateDay(dailyReport: DailyReport) {

    with(earningsUIModel) {
      earnings = dailyReport.orderEarnings
    }

    with(dayUIModel) {
      selectedDailyReport = dailyReport
    }

  }

}