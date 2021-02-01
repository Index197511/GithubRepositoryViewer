package me.index197511.githubrepositoryviewer.shared.data.resource.local

import com.squareup.sqldelight.db.SqlDriver

expect class StarredRepoDatabaseDriverFactory {
    fun createDriver(): SqlDriver
}