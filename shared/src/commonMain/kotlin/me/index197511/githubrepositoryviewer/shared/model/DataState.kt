package me.index197511.githubrepositoryviewer.shared.model

sealed class DataState<out T> {
    object Loading : DataState<Nothing>()
    object Empty : DataState<Nothing>()
    data class Success<T : Any>(val data: T) : DataState<T>()
    data class Error(val exception: String) : DataState<Nothing>()
}