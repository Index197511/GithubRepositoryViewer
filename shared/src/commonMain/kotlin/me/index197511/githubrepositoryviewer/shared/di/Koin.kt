package me.index197511.githubrepositoryviewer.shared.di

import me.index197511.githubrepositoryviewer.shared.data.repository.GithubRepository
import me.index197511.githubrepositoryviewer.shared.data.repository.StarredRepoRepository
import me.index197511.githubrepositoryviewer.shared.data.resource.local.StarredRepoDatabase
import me.index197511.githubrepositoryviewer.shared.data.resource.local.StarredRepoDatabaseDriverFactory
import me.index197511.githubrepositoryviewer.shared.data.resource.remote.GithubService
import me.index197511.githubrepositoryviewer.shared.data.resource.remote.IGithubService
import me.index197511.githubrepositoryviewer.shared.model.repository.IGithubRepository
import me.index197511.githubrepositoryviewer.shared.model.repository.IStarredRepoRepository
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val commonModule = module {
    single<IGithubService> { GithubService() }
    single<IGithubRepository> { GithubRepository() }
}

// call from Android
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule)
}

// call from iOS
fun initKoin(databaseDriverFactory: StarredRepoDatabaseDriverFactory) = initKoin {
    modules(module {
        single { StarredRepoDatabase(databaseDriverFactory) }
        single<IStarredRepoRepository> { StarredRepoRepository(get()) }
    })
}
