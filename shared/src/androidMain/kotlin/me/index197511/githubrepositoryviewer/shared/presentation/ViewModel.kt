package me.index197511.githubrepositoryviewer.shared.presentation

import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.ViewModel as AndroidViewModel
import androidx.lifecycle.viewModelScope as AndroidViewModelScope

actual abstract class ViewModel actual constructor(coroutineScope: CoroutineScope?) :
    AndroidViewModel() {
    actual val viewModelScope = coroutineScope ?: AndroidViewModelScope
}