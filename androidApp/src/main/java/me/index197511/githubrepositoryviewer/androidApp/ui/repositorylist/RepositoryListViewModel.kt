package me.index197511.githubrepositoryviewer.androidApp.ui.repositorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.repository.IGithubRepository

class RepositoryListViewModel(private val repository: IGithubRepository) : ViewModel() {

    @ExperimentalCoroutinesApi
    private val _repositories: MutableStateFlow<DataState> =
        MutableStateFlow(DataState.Empty)

    @ExperimentalCoroutinesApi
    val repositories: StateFlow<DataState>
        get() = _repositories

    @ExperimentalCoroutinesApi
    fun getRepositories() {
        viewModelScope.launch {
            repository.getRepositories().collect {
                _repositories.value = it
            }
        }
    }
}