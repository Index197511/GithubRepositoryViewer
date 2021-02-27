package me.index197511.githubrepositoryviewer.androidApp.di

import me.index197511.githubrepositoryviewer.androidApp.ui.repositorylist.RepositoryListViewModel
import me.index197511.githubrepositoryviewer.androidApp.ui.starredrepositorylist.StarredRepositoryListViewModel
import me.index197511.githubrepositoryviewer.shared.data.repository.StarredRepoRepository
import me.index197511.githubrepositoryviewer.shared.data.resource.local.StarredRepoDatabase
import me.index197511.githubrepositoryviewer.shared.data.resource.local.StarredRepoDatabaseDriverFactory
import me.index197511.githubrepositoryviewer.shared.model.repository.IStarredRepoRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { StarredRepoDatabase(StarredRepoDatabaseDriverFactory(get())) }
    single<IStarredRepoRepository> { StarredRepoRepository() }
    viewModel { RepositoryListViewModel(get(), get()) }
    viewModel { StarredRepositoryListViewModel(get()) }
}