package me.index197511.githubrepositoryviewer.androidApp.ui.common.repository

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.onCommit
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.index197511.githubrepositoryviewer.androidApp.ui.util.Space

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