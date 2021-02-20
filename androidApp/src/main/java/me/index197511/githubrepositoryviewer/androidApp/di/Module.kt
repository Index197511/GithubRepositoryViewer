package me.index197511.githubrepositoryviewer.androidApp.di

import me.index197511.githubrepositoryviewer.androidApp.ui.repositorylist.RepositoryListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { RepositoryListViewModel(get()) }
}