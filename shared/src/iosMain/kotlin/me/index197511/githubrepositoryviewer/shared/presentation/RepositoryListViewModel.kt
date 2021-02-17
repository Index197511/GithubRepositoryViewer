package me.index197511.githubrepositoryviewer.shared.presentation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository

@ExperimentalCoroutinesApi
fun RepositoryListViewModel.observe(block: (DataState) -> Unit) {
    this.repositories.onEach { block(it) }.launchIn(this.viewModelScope)
}