package me.index197511.githubrepositoryviewer.shared.model

sealed class DataState {
    object Loading : DataState()
    object Empty : DataState()
    data class Success(val data: List<Repository>) : DataState()
    data class Error(val exception: String) : DataState()
}