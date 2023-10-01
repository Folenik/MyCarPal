package com.mosz.wikirandom.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val randomArticleService: RandomArticleService) {
    suspend fun getRandomArticle() =
        randomArticleService.getRandomArticle()
}