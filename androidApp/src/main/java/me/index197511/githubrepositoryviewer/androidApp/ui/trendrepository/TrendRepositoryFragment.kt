package me.index197511.githubrepositoryviewer.androidApp.ui.trendrepository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import androidx.fragment.app.Fragment
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
    Column {
        Text(text = repository.name)
        Text(text = repository.language.language)
    }
}

@Preview
@Composable
fun RepositoryListItem_Preview() {
    RepositoryListItem(
        repository = Repository(
            author = "author",
            name = "Repository",
            url = "https://repository....",
            description = "This Repository is Sample.",
            language = Language(language = "Kotlin", color = "#FFFFFF"),
            stars = 2
        )
    )
}