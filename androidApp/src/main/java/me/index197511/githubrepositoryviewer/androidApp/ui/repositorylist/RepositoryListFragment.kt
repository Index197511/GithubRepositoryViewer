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
import me.index197511.githubrepositoryviewer.shared.model.DataState
import me.index197511.githubrepositoryviewer.shared.model.Repository

class RepositoryListFragment : Fragment() {
    private val viewModel = RepositoryListViewModel()

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
    val repositories: DataState by viewModel.repositories.collectAsState(initial = DataState.Empty)

    when (val res: DataState = repositories) {
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
            ErrorView(onClick = { viewModel.getRepositories() })
        }
        else -> {
        }
    }
}

@Composable
fun ErrorView(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Oops, an error occurred", textAlign = TextAlign.Center, color = Color.Gray)
            Space(height = 4)
            Text(text = "please try again", textAlign = TextAlign.Center, color = Color.Gray)
            Space(height = 8)
            Button(onClick = onClick) {
                Text(text = "RETRY")
            }
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
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

@Composable
fun RepositoryName(name: String) {
    Text(
        text = name,
        fontSize = 24.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Description(description: String) {
    Text(
        text = description,
        fontSize = 16.sp,
        fontFamily = FontFamily.SansSerif,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.wrapContentHeight()
    )
}

@Composable
fun Star(star: Int) {
    Text(
        text = "Star: $star",
        fontSize = 12.sp,
        color = Color.Gray
    )
}

@Composable
fun Language(language: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .preferredSize(12.dp)
                .clip(CircleShape)
                .background(Color.fromString("#FF00FF"))
        )
        Space(width = 4)
        Text(text = language, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun User(author: String, avatarUrl: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Avatar(avatarUrl = avatarUrl)
        Space(width = 8)
        Text(text = author, textAlign = TextAlign.Center, fontSize = 16.sp)
    }
}

@Composable
fun Avatar(avatarUrl: String) {
    val image = remember { mutableStateOf<ImageBitmap?>(null) }
    val context = AmbientContext.current

    onCommit(avatarUrl) {
        val glide = Glide.with(context)
        val job = CoroutineScope(Dispatchers.Main).launch {
            glide.asBitmap().load(avatarUrl).into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    image.value = resource.asImageBitmap()
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    image.value = null
                }
            })
        }
        onDispose {
            image.value = null
            job.cancel()
        }
    }

    image.value?.let {
        Surface(
            modifier = Modifier
                .preferredSize(30.dp)
                .clip(CircleShape),
            color = MaterialTheme.colors.onSurface.copy(0.2f)
        ) {
            Image(bitmap = it)
        }
    }

}

@Composable
private fun Space(width: Int = 0, height: Int = 0) {
    Spacer(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
    )
}

@Preview
@Composable
fun RepositoryListItem_Preview() {
    RepositoryListItem(
        repository = Repository(
            author = "author",
            name = "Repository",
            avatarUrl = "https://github.com/Index197511.png",
            url = "https://repository....",
            description = "This Repository is Sample.",
            language = "Kotlin",
            stars = 2
        )
    )
}