package me.index197511.githubrepositoryviewer.shared.data.resource.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.index197511.githubrepositoryviewer.shared.db.StarredRepository
import me.index197511.githubrepositoryviewer.shared.db.StarredRepositoryDatabase
import me.index197511.githubrepositoryviewer.shared.model.Language
import me.index197511.githubrepositoryviewer.shared.model.Repository

internal class StarredRepositoryDatabase(databaseDriverFactory: StarredRepositoryDatabaseDriverFactory) {
    private val database = StarredRepositoryDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.starredRepositoryDatabaseQueries

    internal fun getAllStarredRepository(): Flow<List<Repository>> {
        return dbQuery.loadAllStarredRepository().asFlow().mapToList()
            .map { e -> e.map { it.toRepository() } }
    }

    internal fun insertStarredRepository(repository: Repository) {
        dbQuery.insertStarredRepository(
            repository.author,
            repository.name,
            repository.url,
            repository.description,
            repository.language.language,
            repository.language.color,
            repository.stars
        )
    }

    internal fun removeStarredRepository(id: Int) {
        dbQuery.removeStarredRepository(id.toLong())
    }
}

fun StarredRepository.toRepository() =
    Repository(
        author,
        name,
        url,
        description,
        Language(language, languageColor),
        stars!!
    )