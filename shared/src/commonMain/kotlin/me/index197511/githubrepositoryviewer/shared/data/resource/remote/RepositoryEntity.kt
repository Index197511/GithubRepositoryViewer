package me.index197511.githubrepositoryviewer.shared.data.resource.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.index197511.githubrepositoryviewer.shared.model.Language
import me.index197511.githubrepositoryviewer.shared.model.Repository

@Serializable
data class RepositoryEntity(
    @SerialName("author")
    val author: String,
    @SerialName("name")
    val name: String,
    @SerialName("avatar")
    val avatar: String,
    @SerialName("url")
    val url: String,
    @SerialName("description")
    val description: String,
    @SerialName("language")
    val language: String,
    @SerialName("languageColor")
    val languageColor: String,
    @SerialName("stars")
    val stars: Int
) {
    fun toModel() =
        Repository(author, name, avatar, url, description, Language(language, languageColor), stars)
}