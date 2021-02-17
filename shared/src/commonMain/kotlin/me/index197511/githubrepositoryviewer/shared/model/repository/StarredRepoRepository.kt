package me.index197511.githubrepositoryviewer.shared.model.repository

import kotlinx.coroutines.flow.Flow
import me.index197511.githubrepositoryviewer.shared.model.Repository

interface IStarredRepoRepository {
    fun getAllStarredRepo(): Flow<List<Repository>>
    suspend fun insertStarredRepo(repository: Repository)
    suspend fun removeStarredRepo(id: Int)
}