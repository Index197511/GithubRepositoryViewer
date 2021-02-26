package me.index197511.githubrepositoryviewer.androidApp.ui.repositorylist

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import me.index197511.githubrepositoryviewer.androidApp.ext.fromString
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
            LazyColumn {
                items(res.data) {
                    RepositoryListItem(repository = it) { repository ->
                        viewModel.starRepository(
                            repository
                        )
                    }
                }
            }
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
fun RepositoryListItem(repository: Repository, onClick: (repository: Repository) -> Unit) {
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