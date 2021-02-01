package me.index197511.githubrepositoryviewer.shared.data.resource.local

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import me.index197511.githubrepositoryviewer.shared.db.StarredRepositoryDatabase

actual class StarredRepoDatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(StarredRepositoryDatabase.Schema, context, "starredRepo.db")
}