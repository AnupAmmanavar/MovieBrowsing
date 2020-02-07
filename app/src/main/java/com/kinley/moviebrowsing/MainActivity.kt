package com.kinley.moviebrowsing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kinley.moviebrowsing.features.home.HomePageViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private val vm: HomePageViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    vm.load()

    vm.movies.observe(this, Observer {
      count.text = "Size ${it.size}"
    })

  }
}
