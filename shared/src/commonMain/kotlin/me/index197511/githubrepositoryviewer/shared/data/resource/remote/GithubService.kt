package me.index197511.githubrepositoryviewer.shared.data.resource.remote

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

interface IGithubService {
    suspend fun getTrendRepositories(): List<RepositoryEntity>
}

class GithubService : IGithubService {
    companion object {
        const val API_URL = "https://api.github.com/search/repositories?q=language:kotlin"
    }

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    override suspend fun getTrendRepositories(): List<RepositoryEntity> =
        httpClient.get(API_URL)
}