package me.index197511.githubrepositoryviewer.shared.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.index197511.githubrepositoryviewer.shared.data.resource.local.StarredRepoDatabase
import me.index197511.githubrepositoryviewer.shared.data.resource.local.toRepository
import me.index197511.githubrepositoryviewer.shared.model.Repository
import me.index197511.githubrepositoryviewer.shared.model.repository.IStarredRepoRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class StarredRepoRepository : IStarredRepoRepository, KoinComponent {
    private val database by inject<StarredRepoDatabase>()
    override fun getAllStarredRepo(): Flow<List<Repository>> =
        database.getAllStarredRepository().map { e -> e.map { it.toRepository() } }

    override suspend fun insertStarredRepo(repository: Repository) =
        database.insertStarredRepository(repository)

    override suspend fun removeStarredRepo(id: Long) =
        database.removeStarredRepository(id)
}