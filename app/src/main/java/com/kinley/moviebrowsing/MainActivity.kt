package com.kinley.moviebrowsing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kinley.moviebrowsing.features.home.HomePageViewModel

class MainActivity : AppCompatActivity() {

  private val vm: HomePageViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    vm.load()
  }
}
