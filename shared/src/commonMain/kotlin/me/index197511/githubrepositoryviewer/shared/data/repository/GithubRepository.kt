package me.index197511.githubrepositoryviewer.shared.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.index197511.githubrepositoryviewer.shared.data.resource.remote.IGithubService
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.repository.IGithubRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GithubRepository : IGithubRepository, KoinComponent {
    private val service by inject<IGithubService>()
    override fun getRepositories(): Flow<DataState> =
        flow {
            emit(DataState.Loading)
            kotlin.runCatching {
                service.getRepositories()
            }
                .onSuccess {
                    emit(DataState.Success(it.items.map { e -> e.toModel() }))
                }
                .onFailure {
                    emit(DataState.Error(it.toString()))
                }
        }
}
