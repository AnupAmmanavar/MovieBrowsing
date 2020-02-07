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

    epoxy_rv.withModels {
      val movies = vm.movies.value ?: arrayListOf()
      movies.forEach {  movie ->
        MovieCellBindingModel_()
          .id(movie.id)
          .movieName(movie.title)
          .addTo(this)
      }
    }

    vm.movies.observe(this, Observer {
      epoxy_rv.requestModelBuild()
    })

  }
}
