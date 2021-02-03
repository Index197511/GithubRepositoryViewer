package me.index197511.githubrepositoryviewer.shared.model

data class Repository(
    val author: String,
    val name: String,
    val avatar: String,
    val url: String,
    val description: String,
    val language: Language,
    val stars: Int
)

data class Language(
    val language: String,
    val color: String
)