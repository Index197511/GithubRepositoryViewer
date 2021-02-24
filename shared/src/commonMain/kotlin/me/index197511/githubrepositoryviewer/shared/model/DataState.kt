package me.index197511.githubrepositoryviewer.shared.model

sealed class DataState {
    object Init : DataState()
    object Loading : DataState()
    data class Success(val data: List<Repository>) : DataState()
    data class Error(val exception: String) : DataState()
}