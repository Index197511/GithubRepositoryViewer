package me.index197511.githubrepositoryviewer.androidApp.ui.common.repository

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.index197511.githubrepositoryviewer.androidApp.ext.fromString
import me.index197511.githubrepositoryviewer.androidApp.ui.util.Space

@Composable
fun Language(language: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(CircleShape)
                .background(Color.fromString("#FF00FF"))
        )
        Space(width = 4)
        Text(text = language, fontSize = 12.sp, color = Color.Gray)
    }
}