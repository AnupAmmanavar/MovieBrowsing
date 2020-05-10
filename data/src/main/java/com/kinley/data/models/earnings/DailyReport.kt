package com.kinley.data.models.earnings

data class DailyReport(
  val id: String,
  val earnings: Double,
  val orders: Int,
  val onlineTime: Int,
  val orderEarnings: List<OrderEarning>
)
