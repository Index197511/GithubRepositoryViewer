package me.index197511.githubrepositoryviewer.shared.data.repository

import kotlinx.coroutines.flow.flow
import me.index197511.githubrepositoryviewer.shared.data.resource.remote.IGithubService
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository
import me.index197511.githubrepositoryviewer.shared.model.repository.IGithubRepository
import me.index197511.githubrepositoryviewer.shared.util.CommonFlow
import me.index197511.githubrepositoryviewer.shared.util.wrap

class GithubRepository(private val service: IGithubService) : IGithubRepository {
    override fun getTrendRepositories(): CommonFlow<DataState<List<Repository>>> =
        flow {
            emit(DataState.Loading)
            kotlin.runCatching {
                service.getTrendRepositories()
            }
                .onSuccess {
                    emit(DataState.Success(it.items.map { e -> e.toModel() }))
                }
                .onFailure {
                    emit(DataState.Error(it.toString()))
                }
        }.wrap()
}
