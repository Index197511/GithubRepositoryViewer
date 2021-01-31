package me.index197511.githubrepositoryviewer.shared.data.resource.remote

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class GithubService {
    companion object {
        const val API_URL = "https://ghapi.huchen.dev/repositories"
    }

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getTrendRepositories(): List<RepositoryEntity> =
        httpClient.get(API_URL)
}