package me.index197511.githubrepositoryviewer.androidApp.ui.repositorylist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository
import me.index197511.githubrepositoryviewer.shared.model.repository.IGithubRepository
import me.index197511.githubrepositoryviewer.shared.model.repository.IStarredRepoRepository

class RepositoryListViewModel(
    private val githubRepository: IGithubRepository,
    private val starredRepoRepository: IStarredRepoRepository
) : ViewModel() {

    @ExperimentalCoroutinesApi
    private val _repositories: MutableStateFlow<DataState> =
        MutableStateFlow(DataState.Empty)

    @ExperimentalCoroutinesApi
    val repositories: StateFlow<DataState>
        get() = _repositories

    @ExperimentalCoroutinesApi
    fun getRepositories() {
        viewModelScope.launch {
            githubRepository.getRepositories().collect {
                _repositories.value = it
            }
        }
    }

    fun starRepository(repository: Repository) {
        Log.i("Index197511", "STAR ${repository.name}")
        viewModelScope.launch {
            starredRepoRepository.insertStarredRepo(repository)
        }
    }
}