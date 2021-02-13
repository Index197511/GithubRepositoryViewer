package me.index197511.githubrepositoryviewer.shared.presentation

import kotlinx.coroutines.CoroutineScope

expect abstract class ViewModel constructor(
    coroutineScope: CoroutineScope? = null
) {
    val viewModelScope: CoroutineScope
}