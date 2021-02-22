package me.index197511.githubrepositoryviewer.androidApp.di

import android.app.Application
import me.index197511.githubrepositoryviewer.shared.di.commonModule
import me.index197511.githubrepositoryviewer.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(commonModule, appModule)
        }
    }
}