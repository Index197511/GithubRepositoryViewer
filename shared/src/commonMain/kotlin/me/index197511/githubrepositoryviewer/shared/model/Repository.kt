package me.index197511.githubrepositoryviewer.shared.model

data class Repository(
    val author: String,
    val name: String,
    val url: String,
    val description: String,
    val language: String,
    val languageColor: String,
    val stars: Int
)