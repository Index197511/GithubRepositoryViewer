package me.index197511.githubrepositoryviewer.shared.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

actual abstract class ViewModel actual constructor(coroutineScope: CoroutineScope?) {
    actual val viewModelScope = coroutineScope ?: MainScope()
}