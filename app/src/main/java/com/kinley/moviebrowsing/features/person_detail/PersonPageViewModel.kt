package com.kinley.moviebrowsing.features.person_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinley.moviebrowsing.models.Person
import com.kinley.moviebrowsing.models.PersonCast
import com.kinley.moviebrowsing.repository.MovieBrowsingRemote
import com.kinley.moviebrowsing.repository.MovieBrowsingRemoteImpl
import kotlinx.coroutines.launch

class PersonPageViewModel : ViewModel() {

    private val repository: MovieBrowsingRemote = MovieBrowsingRemoteImpl()

    private val _pageDate: MutableLiveData<PersonPageUiModel> = MutableLiveData()
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
            val responseModel = repository.getMovieCreditsForPerson(id)
            update { it.copy(castList = responseModel.personCast) }
        }
    }

    fun update(block: (PersonPageUiModel) -> PersonPageUiModel) {
        val uiModel = _pageDate.value ?: PersonPageUiModel(
            null,
            arrayListOf()
        )
        _pageDate.postValue(block.invoke(uiModel))
    }
}

data class PersonPageUiModel(
    val person: Person?,
    val castList: List<PersonCast>
)
