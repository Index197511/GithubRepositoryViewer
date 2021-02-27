package me.index197511.githubrepositoryviewer.androidApp.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import me.index197511.githubrepositoryviewer.androidApp.ui.util.Space

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