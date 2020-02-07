package com.kinley.moviebrowsing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.epoxy.CarouselModelBuilder
import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.carousel
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

      carousel {
        id("popular")
        numViewsToShowOnScreen(2.3f)
        withModelsFrom(movies) { movie ->
          MovieCellBindingModel_()
            .id(movie.id)
            .movie(movie)
        }
      }

    }

    vm.movies.observe(this, Observer {
      epoxy_rv.requestModelBuild()
    })

  }
}

/** For use in the buildModels method of EpoxyController. A shortcut for creating a Carousel model, initializing it, and adding it to the controller.
 *
 */
inline fun EpoxyController.carousel(modelInitializer: CarouselModelBuilder.() -> Unit) {
  CarouselModel_().apply {
    modelInitializer()
  }.addTo(this)
}

/** Add models to a CarouselModel_ by transforming a list of items into EpoxyModels.
 *
 * @param items The items to transform to models
 * @param modelBuilder A function that take an item and returns a new EpoxyModel for that item.
 */
inline fun <T> CarouselModelBuilder.withModelsFrom(
  items: List<T>,
  modelBuilder: (T) -> EpoxyModel<*>
) {
  models(items.map { modelBuilder(it) })
}
