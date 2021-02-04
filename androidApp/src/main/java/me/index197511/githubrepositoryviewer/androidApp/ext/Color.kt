package me.index197511.githubrepositoryviewer.androidApp.ext

import androidx.compose.ui.graphics.Color

fun Color.Companion.fromString(rawColor: String) =
    Color(android.graphics.Color.parseColor(rawColor))