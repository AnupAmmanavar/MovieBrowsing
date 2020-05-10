package com.kinley.data.repository

import android.os.Build
import com.kinley.data.models.earnings.DailyReport
import com.kinley.data.models.earnings.OrderEarning
import com.kinley.data.models.earnings.WeeklyEarningReport
import java.util.*
import java.util.concurrent.ThreadLocalRandom


interface OrderEarningsRepository {
  suspend fun getWeeklyReport(): WeeklyEarningReport

  fun getWeeklyReportList(): List<WeeklyEarningReport>
}


class OrderEarningRepositoryImpl : OrderEarningsRepository {
  override suspend fun getWeeklyReport(): WeeklyEarningReport {
    TODO("Not yet implemented")
  }

  override fun getWeeklyReportList(): List<WeeklyEarningReport> {
    return DataProvider.getWeeklyReports()
  }

}

object DataProvider {

  fun getWeeklyReports() = (0..df.randomInt()).map {
    this.getWeeklyReport(it)
  }

  private fun getWeeklyReport(index: Int): WeeklyEarningReport {
    val dailyReports = getDailyReports()
    return WeeklyEarningReport(
      id = "$index",
      startDate = "12-05-202",
      endDate = "19-05-2020",
      earnings = dailyReports.map { it.earnings }.reduce { acc, i -> acc + i },
      orders = dailyReports.map { it.orders }.reduce { acc, i -> acc + i },
      onlineTime = dailyReports.map { it.onlineTime }.reduce { acc, i -> acc + i },
      dailyReports = dailyReports
    )
  }

  private fun getDailyReports() = arrayListOf("Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat").map(this::getDailyReport)


  private fun getDailyReport(index: String): DailyReport {
    val orderEarnings: List<OrderEarning> = getOrderEarnings(df.randomInt())
    return DailyReport(
      id = index,
      earnings = orderEarnings.map { it.amount }.reduce { acc, d -> acc + d },
      onlineTime = df.randomInt() * df.randomInt(),
      orderEarnings = orderEarnings,
      orders = orderEarnings.count()
    )
  }

  private fun getOrderEarnings(count: Int) = (0..count).map(this::getOrderEarning)

  private fun getOrderEarning(index: Int) = OrderEarning("$index", "Trip", "10-05-2020", DataFactory.randomDouble())
}


object DataFactory {

  fun randomString(): String {
    return UUID.randomUUID().toString()
  }

  fun randomInt(): Int {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      ThreadLocalRandom.current().nextInt(3, 20 + 1)
    } else {
      Random().nextInt(20 + 1)
    }
  }

  fun randomDouble(): Double {
    return randomInt().toDouble()
  }
}

typealias df = DataFactory
