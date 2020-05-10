package com.kinley.jetpackui.jetcompose.components

import androidx.compose.Model
import androidx.ui.core.Modifier
import androidx.ui.core.Text
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Spacer
import androidx.ui.material.surface.Card
import com.kinley.data.models.earnings.OrderEarning
import com.kinley.jetpackui.jetcompose.HStack
import com.kinley.jetpackui.jetcompose.VStack
import com.kinley.jetpackui.jetcompose.jetpack_views.ComposableView

class EarningUXComponent(
  private val vmDelegate: VMDelegate,
  private val earningUIModel: EarningUIModel
) : UXComponent<UIDelegate, VMDelegate, EarningUIModel>(vmDelegate, earningUIModel) {

  override fun composableView(delegate: UIDelegate): ComposableView = {
    VEarningView(earningUIModel = earningUIModel)
  }
}

private fun VEarningView(earningUIModel: EarningUIModel) {

  Card(shape = RoundedCornerShape(10)) {

    VStack {

      earningUIModel.earnings.forEach { orderEarning ->
        HStack {
          Text(text = orderEarning.description)
          Spacer(modifier = Modifier.None)
          Text(text = "Rs ${orderEarning.amount}")
        }
      }
    }
  }

}

@Model
data class EarningUIModel(
  var earnings: List<OrderEarning>
)
