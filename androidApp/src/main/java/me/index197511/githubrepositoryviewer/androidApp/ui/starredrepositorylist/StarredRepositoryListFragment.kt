package me.index197511.githubrepositoryviewer.androidApp.ui.starredrepositorylist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.fragment.app.Fragment
import kotlinx.coroutines.InternalCoroutinesApi
import me.index197511.githubrepositoryviewer.androidApp.ui.common.ErrorView
import me.index197511.githubrepositoryviewer.androidApp.ui.common.LoadingView
import me.index197511.githubrepositoryviewer.androidApp.ui.common.repository.*
import me.index197511.githubrepositoryviewer.androidApp.ui.util.Space
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository
import org.koin.android.viewmodel.ext.android.viewModel

class StarredRepositoryListFragment : Fragment() {
    private val viewModel by viewModel<StarredRepositoryListViewModel>()

    @InternalCoroutinesApi
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
                StarredRepositoryListView(viewModel = viewModel)
            }
        }
        viewModel.getStarredRepositories()
    }
}

@InternalCoroutinesApi
@Composable
fun StarredRepositoryListView(viewModel: StarredRepositoryListViewModel) {
    val starredRepositories: DataState by viewModel.starredRepositories.collectAsState(initial = DataState.Init)
    when (val res: DataState = starredRepositories) {
        is DataState.Loading -> {
            LoadingView()
        }
        is DataState.Success -> {
            LazyColumn {
                items(res.data) {
                    RepositoryListItem(repository = it)
                }
            }
        }
        is DataState.Error -> {
            Log.i("Index197511", res.exception)
            ErrorView(onClick = { viewModel.getStarredRepositories() })
        }
        else -> {
        }
    }
}

@Composable
fun RepositoryListItem(repository: Repository) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { Log.i("Index197511", "CLICKED") }
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
