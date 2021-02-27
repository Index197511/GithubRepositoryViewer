package me.index197511.githubrepositoryviewer.androidApp.ui.starredrepositorylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository
import me.index197511.githubrepositoryviewer.shared.model.repository.IStarredRepoRepository

class StarredRepositoryListViewModel(private val starredRepoRepository: IStarredRepoRepository) :
    ViewModel() {
    private val _starredRepositories: MutableStateFlow<DataState> =
        MutableStateFlow(DataState.Init)
    val starredRepositories: StateFlow<DataState>
        get() = _starredRepositories

    @InternalCoroutinesApi
    fun getStarredRepositories() {
        viewModelScope.launch {
            _starredRepositories.value = DataState.Loading
            starredRepoRepository.getAllStarredRepo().collect {
                Log.i("Index197511", it.toString())
                _starredRepositories.value = DataState.Success(it)
            }
        }
    }

    fun unstarRepository(repository: Repository) {
        viewModelScope.launch {
            starredRepoRepository.removeStarredRepo(repository.id)
        }
    }
}