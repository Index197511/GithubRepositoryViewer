package me.index197511.githubrepositoryviewer.shared.data.resource.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import me.index197511.githubrepositoryviewer.shared.db.StarredRepository
import me.index197511.githubrepositoryviewer.shared.db.StarredRepositoryDatabase
import me.index197511.githubrepositoryviewer.shared.model.Repository

class StarredRepoDatabase(databaseDriverFactory: StarredRepoDatabaseDriverFactory) {
    private val database = StarredRepositoryDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.starredRepositoryDatabaseQueries

    internal fun getAllStarredRepository(): Flow<List<StarredRepository>> =
        dbQuery.loadAllStarredRepository().asFlow().mapToList()

    internal fun insertStarredRepository(repository: Repository) {
        dbQuery.upsertStarredRepository(
            id = repository.id,
            author = repository.author,
            avatarUrl = repository.avatarUrl,
            name = repository.name,
            url = repository.url,
            description = repository.description,
            language = repository.language,
            stars = repository.stars
        )
    }

    internal fun removeStarredRepository(id: Long) {
        dbQuery.removeStarredRepository(id)
    }
}

fun StarredRepository.toRepository() =
    Repository(
        id = id,
        author = author,
        avatarUrl = avatarUrl,
        name = name,
        url = url,
        description = description,
        language = language,
        stars = stars
    )