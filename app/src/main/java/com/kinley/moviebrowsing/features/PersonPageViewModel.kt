package com.kinley.moviebrowsing.features

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.moviebrowsing.models.Cast
import com.kinley.moviebrowsing.models.Person
import com.kinley.moviebrowsing.repository.MovieBrowsingRemote
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.launch

class PersonPageViewModel : ViewModel() {

    private val repository: MovieBrowsingRemote = MovieBrowsingRemoteImpl()

    val _pageDate: MutableLiveData<PersonPageUiModel> = MutableLiveData()
    val pageData : LiveData<PersonPageUiModel>
    get() {
        return _pageDate
    }

    fun load(id: Long) {
        loadPersonDetails(id)
        loadMovieCredits(id)
    }

    private fun loadPersonDetails(id: Long) {
        viewModelScope.launch {
            val person = repository.getPersonDetails(id)
            update { it.copy(person = person) }
        }
    }

    private fun loadMovieCredits(id: Long) {
        viewModelScope.launch {
            val castedList = repository.getMovieCreditsForPerson(id)
            update { it.copy(castList = castedList.cast) }
        }
    }

    fun update(block: (PersonPageUiModel) -> PersonPageUiModel) {
        val uiModel = _pageDate.value ?: PersonPageUiModel(null, arrayListOf())
        _pageDate.postValue(block.invoke(uiModel))
    }
}

data class PersonPageUiModel(
    val person: Person?,
    val castList: List<Cast>
)
