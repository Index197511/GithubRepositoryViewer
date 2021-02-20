package me.index197511.githubrepositoryviewer.shared.data.resource.local

import com.squareup.sqldelight.db.SqlDriver

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class StarredRepoDatabaseDriverFactory {
    fun createDriver(): SqlDriver
}