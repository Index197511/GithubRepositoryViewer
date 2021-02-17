package me.index197511.githubrepositoryviewer.shared.model.repository

import kotlinx.coroutines.flow.Flow
import me.index197511.githubrepositoryviewer.shared.model.DataState

interface IGithubRepository {
    fun getRepositories(): Flow<DataState>
}