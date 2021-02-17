package me.index197511.githubrepositoryviewer.shared.util

import io.ktor.utils.io.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Flow<T>.wrap(): CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val origin: Flow<T>) : Flow<T> by origin {
    fun watch(block: (T) -> Unit): Closeable {
        val job = Job(/*ConferenceService.coroutineContext[Job]*/)

        onEach {
            block(it)
        }.launchIn(CoroutineScope(job))

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}

@InternalCoroutinesApi
suspend fun <T> CommonFlow<T>.collectTest(block: (Any?) -> Unit) {
    withContext(Dispatchers.Default) {
        this@collectTest.collect {
            block(it)
        }
    }
}