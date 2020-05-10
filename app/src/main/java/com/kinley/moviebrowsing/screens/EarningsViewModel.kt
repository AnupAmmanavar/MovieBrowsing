package com.kinley.moviebrowsing.screens

import com.kinley.data.models.earnings.DailyReport
import com.kinley.data.models.earnings.WeeklyEarningReport
import com.kinley.data.repository.OrderEarningRepositoryImpl
import com.kinley.data.repository.OrderEarningsRepository
import com.kinley.jetpackui.jetcompose.components.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class EarningsViewModel : WeekVMDelegate, DayVMDelegate, CoroutineScope by CoroutineScope(Dispatchers.Main) {

  var weekUXComponent: WeekUXComponent
  var dayUXComponent: DayUXComponent
  var earningUXComponent: EarningUXComponent

  private val earningsRepository: OrderEarningsRepository = OrderEarningRepositoryImpl()

  private var weekUIModel: WeekUIModel = WeekUIModel(emptyList(), null)
  private var dayUIModel: DayUIModel = DayUIModel(emptyList(), null)

  private val earningsUIModel = EarningUIModel(arrayListOf())

  init {

    val reports = earningsRepository.getWeeklyReportList()
    weekUIModel = WeekUIModel(reports, null)

    // Constructing the components
    weekUXComponent = WeekUXComponent(this@EarningsViewModel, weekUIModel)
    dayUXComponent = DayUXComponent(this@EarningsViewModel, dayUIModel)
    earningUXComponent = EarningUXComponent(this@EarningsViewModel, earningsUIModel)


  }

  override fun onWeekSelected(weeklyEarningReport: WeeklyEarningReport) {
    // Resetting the day
    resetDay()

    dayUIModel.dailyReport = weeklyEarningReport.dailyReports
    weekUIModel.selectedWeeklyEarningReport = weeklyEarningReport
  }

  override fun onDaySelected(day: DailyReport) {
    dayUIModel.selectedDailyReport = day

    earningsUIModel.earnings = dayUIModel.selectedDailyReport?.orderEarnings ?: arrayListOf()
  }

  private fun resetDay() {
    dayUIModel.reset()
    resetEarnings()
  }

  private fun resetEarnings() {
    earningsUIModel.earnings = emptyList()
  }

}