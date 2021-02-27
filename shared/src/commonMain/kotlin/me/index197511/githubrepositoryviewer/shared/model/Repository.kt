package me.index197511.githubrepositoryviewer.shared.model

data class Repository(
    val id: Long,
    val author: String,
    val avatarUrl: String,
    val name: String,
    val url: String,
    val description: String?,
    val language: String,
    val stars: Int
)