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

  private val earningPageUIModel: EarningPageUIModel

  init {

    earningPageUIModel = EarningPageUIModel(earningsRepository.getWeeklyReportList())

    // Constructing the components
    weekUXComponent = WeekUXComponent(this@EarningsViewModel, earningPageUIModel.weekUIModel)
    dayUXComponent = DayUXComponent(this@EarningsViewModel, earningPageUIModel.dayUIModel)
    earningUXComponent = EarningUXComponent(this@EarningsViewModel, earningPageUIModel.earningsUIModel)
  }

  override fun onWeekSelected(weeklyEarningReport: WeeklyEarningReport) {
    earningPageUIModel.updateWeek(weeklyEarningReport = weeklyEarningReport)
  }

  override fun onDaySelected(dailyReport: DailyReport) {
    earningPageUIModel.updateDay(dailyReport = dailyReport)
  }

}

