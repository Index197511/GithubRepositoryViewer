package me.index197511.githubrepositoryviewer.shared.di

import me.index197511.githubrepositoryviewer.shared.data.repository.GithubRepository
import me.index197511.githubrepositoryviewer.shared.data.resource.remote.GithubService
import me.index197511.githubrepositoryviewer.shared.data.resource.remote.IGithubService
import me.index197511.githubrepositoryviewer.shared.model.repository.IGithubRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val commonModule = module {
    single<IGithubService> { GithubService() }
    single<IGithubRepository> { GithubRepository() }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
}

fun initKoin() = initKoin {}
