package com.mosz.wikirandom.ui.randomArticle

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.mosz.wikirandom.ui.components.ProgressIndicator

@Composable
fun RandomArticleScreen(viewModel: RandomArticleViewModel) {
    when (val randomArticleState = viewModel.randomArticle.collectAsState().value) {
        is RandomArticleState.Loading -> ProgressIndicator(modifier = Modifier.alpha(1f))
        is RandomArticleState.Success -> {
            Text(text = "${randomArticleState.randomArticleResponse.title} | ${randomArticleState.randomArticleResponse.description}")
        }
        is RandomArticleState.Error -> {
            Text(text = "Your error is: ${randomArticleState.message}")
        }
    }
}
