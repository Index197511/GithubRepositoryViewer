package me.index197511.githubrepositoryviewer.androidApp.ui.repositorylist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.fragment.app.Fragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.index197511.githubrepositoryviewer.androidApp.ui.common.ErrorView
import me.index197511.githubrepositoryviewer.androidApp.ui.common.LoadingView
import me.index197511.githubrepositoryviewer.androidApp.ui.common.repository.*
import me.index197511.githubrepositoryviewer.androidApp.ui.util.Space
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository
import org.koin.android.viewmodel.ext.android.viewModel

class RepositoryListFragment : Fragment() {
    private val viewModel by viewModel<RepositoryListViewModel>()

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setContent {
            MaterialTheme {
                RepositoryListView(viewModel)
            }
        }
        viewModel.getRepositories()
    }
}

@ExperimentalCoroutinesApi
@Composable
fun RepositoryListView(viewModel: RepositoryListViewModel) {
    val repositories: DataState by viewModel.repositories.collectAsState(initial = DataState.Init)

    when (val res: DataState = repositories) {
        is DataState.Loading -> {
            LoadingView()
        }
        is DataState.Success -> {
            RepositoryList(repositories = res.data, onClick = { viewModel.starRepository(it) })
        }
        is DataState.Error -> {
            Log.i("Index197511", res.exception)
            ErrorView(onClick = { viewModel.getRepositories() })
        }
        else -> {
        }
    }
}

@Composable
fun RepositoryList(repositories: List<Repository>, onClick: (repository: Repository) -> Unit) {
    val context = LocalContext.current

    LazyColumn {
        items(repositories) { repository ->
            Repository(
                repository = repository,
                onClick = {
                    onClick(repository)
                    Toast.makeText(context, "starred ${repository.name}", Toast.LENGTH_SHORT)
                        .show()
                }
            )
        }
    }
}


@Composable
fun Repository(repository: Repository, onClick: (repository: Repository) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick(repository) }
        .wrapContentHeight()
        .padding(16.dp)
        .zIndex(8f)
    ) {
        User(author = repository.author, avatarUrl = repository.avatarUrl)
        Space(height = 4)
        RepositoryName(name = repository.name)
        repository.description?.let {
            Space(height = 4)
            Description(description = it)
        }
        Space(height = 8)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Language(language = repository.language)
            Space(width = 8)
            Star(star = repository.stars)
        }
    }
}