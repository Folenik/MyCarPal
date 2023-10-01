package com.mosz.wikirandom.ui.randomArticle

import com.mosz.wikirandom.data.model.RandomArticleResponse

sealed class RandomArticleState {
    data object Loading : RandomArticleState()
    data class Error(val message: String) : RandomArticleState()
    data class Success(val randomArticleResponse: RandomArticleResponse) : RandomArticleState()
}
