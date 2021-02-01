package me.index197511.githubrepositoryviewer.shared.data.resource.local

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import me.index197511.githubrepositoryviewer.shared.db.StarredRepositoryDatabase

actual class StarredRepoDatabaseDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(StarredRepositoryDatabase.Schema, "starredRepo.db")
}