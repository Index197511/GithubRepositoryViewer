package me.index197511.githubrepositoryviewer.shared.model.repository

import kotlinx.coroutines.flow.Flow
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository

interface IGithubRepository {
    fun getTrendRepositories(): Flow<DataState<List<Repository>>>
}