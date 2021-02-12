package me.index197511.githubrepositoryviewer.shared.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.index197511.githubrepositoryviewer.shared.data.resource.local.StarredRepoDatabase
import me.index197511.githubrepositoryviewer.shared.data.resource.local.toRepository
import me.index197511.githubrepositoryviewer.shared.model.Repository
import me.index197511.githubrepositoryviewer.shared.model.repository.IStarredRepoRepository
import me.index197511.githubrepositoryviewer.shared.util.CommonFlow
import me.index197511.githubrepositoryviewer.shared.util.wrap

class StarredRepoRepository(private val database: StarredRepoDatabase) :
    IStarredRepoRepository {
    override fun getAllStarredRepo(): CommonFlow<List<Repository>> =
        database.getAllStarredRepository().map { e -> e.map { it.toRepository() } }.wrap()

    override suspend fun insertStarredRepo(repository: Repository) =
        withContext(Dispatchers.Default) {
            database.insertStarredRepository(repository)
        }

    override suspend fun removeStarredRepo(id: Int) =
        withContext(Dispatchers.Default) {
            database.removeStarredRepository(id.toLong())
        }
}