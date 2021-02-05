package me.index197511.githubrepositoryviewer.shared.data.resource.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import me.index197511.githubrepositoryviewer.shared.model.Repository

@Serializable
data class RepositoriesEntity(
    @SerialName("items")
    val items: List<RepositoryEntity>
)

@Serializable
data class RepositoryEntity(
    @SerialName("owner")
    val owner: OwnerEntity,
    @SerialName("name")
    val name: String,
    @SerialName("html_url")
    val url: String,
    @SerialName("description")
    val description: String?,
    @SerialName("language")
    val language: String,
    @SerialName("stargazers_count")
    val stars: Int,
) {
    fun toModel() =
        Repository(
            author = owner.author,
            avatarUrl = owner.avatar,
            name = name,
            url = url,
            description = description,
            language = language,
            stars = stars,
        )
}

@Serializable
data class OwnerEntity(
    @SerialName("login")
    val author: String,
    @SerialName("avatar_url")
    val avatar: String,
)