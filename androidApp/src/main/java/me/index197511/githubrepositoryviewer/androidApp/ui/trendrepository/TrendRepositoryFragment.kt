package me.index197511.githubrepositoryviewer.androidApp.ui.trendrepository

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.viewModel
import androidx.compose.ui.zIndex
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.index197511.githubrepositoryviewer.shared.model.Language
import me.index197511.githubrepositoryviewer.shared.model.Repository

class TrendRepositoryFragment : Fragment() {
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
            Text("Hi")
        }
    }
}

@Composable
fun TrendRepositoryView() {
    val viewModel: TrendRepositoryViewModel = viewModel()

}

@Composable
fun RepositoryListItem(repository: Repository) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable { Log.i("Index197511", "CLICKED") }
        .padding(8.dp)
        .zIndex(8f)
    ) {
        User(author = repository.author, avatarUrl = repository.avatar)
        Space(height = 8)
        RepositoryName(name = repository.name)
        Space(height = 8)
        Row(modifier = Modifier.fillMaxWidth()) {
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
        fontSize = 20.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.fillMaxWidth()
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
fun Language(language: Language) {
    Row {
        Box(
            modifier = Modifier
                .preferredSize(20.dp)
                .clip(CircleShape)
                .background(Color.Red)
        )
        Text(text = language.language, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun User(author: String, avatarUrl: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
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
                .preferredSize(30.dp),
            color = MaterialTheme.colors.onSurface.copy(0.2f)
        ) {
            Log.i("Index197511", "IMAGE")
            Image(it)
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
            avatar = "https://github.com/Index197511.png",
            url = "https://repository....",
            description = "This Repository is Sample.",
            language = Language(language = "Kotlin", color = "#FFFFFF"),
            stars = 2
        )
    )
}