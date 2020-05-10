@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.kinley.moviebrowsing.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import com.kinley.data.models.earnings.WeeklyEarningReport
import com.kinley.jetpackui.jetcompose.VStack
import com.kinley.jetpackui.jetcompose.components.*
import com.kinley.jetpackui.jetcompose.jetpack_views.render

class EarningsComposeActivity : AppCompatActivity(), WeekUIDelegate {

  private val vm = EarningsViewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        VStack {
          vm.weekUXComponent.composableView(this).render()

          vm.dayUXComponent.composableView(this).render()

          vm.earningUXComponent.composableView(this).render()
        }
      }
    }
  }

  override fun onWeekSelected(weeklyEarningReport: WeeklyEarningReport) {
    vm.onWeekSelected(weeklyEarningReport)
  }
}

