package com.mosz.wikirandom.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.mosz.wikirandom.ui.randomArticle.RandomArticleScreen
import com.mosz.wikirandom.ui.randomArticle.RandomArticleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val randomArticleViewModel by viewModels<RandomArticleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomArticleScreen(viewModel = randomArticleViewModel)
        }
    }
}