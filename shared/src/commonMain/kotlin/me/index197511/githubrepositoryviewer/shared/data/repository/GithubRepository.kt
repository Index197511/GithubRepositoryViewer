package me.index197511.githubrepositoryviewer.shared.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.index197511.githubrepositoryviewer.shared.data.resource.remote.IGithubService
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository
import me.index197511.githubrepositoryviewer.shared.model.repository.IGithubRepository

class GithubRepository(private val service: IGithubService) : IGithubRepository {
    override fun getTrendRepositories(): Flow<DataState<List<Repository>>> =
        flow {
            emit(DataState.Loading)
            kotlin.runCatching {
                service.getTrendRepositories()
            }
                .onSuccess {
                    emit(DataState.Success(it.map { e -> e.toModel() }))
                }
                .onFailure {
                    emit(DataState.Error(it.toString()))
                }
        }
}
