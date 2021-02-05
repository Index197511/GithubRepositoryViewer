package me.index197511.githubrepositoryviewer.androidApp.ui.trendrepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository
import me.index197511.githubrepositoryviewer.shared.model.repository.IGithubRepository

class TrendRepositoryViewModel(private val repository: IGithubRepository) : ViewModel() {
    private val _trendRepos: MutableStateFlow<DataState<List<Repository>>> =
        MutableStateFlow(DataState.Empty)
    val trendRepos: Flow<DataState<List<Repository>>>
        get() = _trendRepos

    fun getTrendRepository() {
        viewModelScope.launch {
            repository.getTrendRepositories().collect {
                _trendRepos.value = it
            }
        }
    }
}