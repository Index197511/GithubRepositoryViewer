package me.index197511.githubrepositoryviewer.shared.model.repository

import me.index197511.githubrepositoryviewer.shared.model.Repository
import me.index197511.githubrepositoryviewer.shared.util.CommonFlow

interface IStarredRepoRepository {
    fun getAllStarredRepo(): CommonFlow<List<Repository>>
    suspend fun insertStarredRepo(repository: Repository)
    suspend fun removeStarredRepo(id: Int)
}