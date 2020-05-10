package com.kinley.data.models.earnings

import java.util.*

data class WeeklyEarningReport(
  val id: String,
  val startDate: String,
  val endDate: String,
  val earnings: Double,
  val orders: Int,
  val onlineTime: Int,
  val dailyReports: List<DailyReport>
)