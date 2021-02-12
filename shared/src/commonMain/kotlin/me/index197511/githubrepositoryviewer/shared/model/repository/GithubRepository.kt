package me.index197511.githubrepositoryviewer.shared.model.repository

import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository
import me.index197511.githubrepositoryviewer.shared.util.CommonFlow

interface IGithubRepository {
    fun getTrendRepositories(): CommonFlow<DataState<List<Repository>>>
}