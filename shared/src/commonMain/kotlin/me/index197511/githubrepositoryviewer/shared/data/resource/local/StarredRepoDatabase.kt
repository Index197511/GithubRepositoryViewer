package me.index197511.githubrepositoryviewer.shared.data.resource.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import me.index197511.githubrepositoryviewer.shared.db.StarredRepository
import me.index197511.githubrepositoryviewer.shared.db.StarredRepositoryDatabase
import me.index197511.githubrepositoryviewer.shared.model.Language
import me.index197511.githubrepositoryviewer.shared.model.Repository

class StarredRepoDatabase(databaseDriverFactory: StarredRepoDatabaseDriverFactory) {
    private val database = StarredRepositoryDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.starredRepositoryDatabaseQueries

    internal fun getAllStarredRepository(): Flow<List<StarredRepository>> =
        dbQuery.loadAllStarredRepository().asFlow().mapToList()

    internal fun insertStarredRepository(repository: Repository) {
        dbQuery.insertStarredRepository(
            repository.author,
            repository.name,
            repository.avatar,
            repository.url,
            repository.description,
            repository.language.language,
            repository.language.color,
            repository.stars
        )
    }

    internal fun removeStarredRepository(id: Long) {
        dbQuery.removeStarredRepository(id)
    }
}

fun StarredRepository.toRepository() =
    Repository(
        author,
        name,
        avatar,
        url,
        description,
        Language(language, languageColor),
        stars!!
    )