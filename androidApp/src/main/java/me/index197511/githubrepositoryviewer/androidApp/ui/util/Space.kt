package me.index197511.githubrepositoryviewer.androidApp.ui.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Space(width: Int = 0, height: Int = 0) {
    Spacer(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp)
    )
}