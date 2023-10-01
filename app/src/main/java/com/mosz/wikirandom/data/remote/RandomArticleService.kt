package com.mosz.wikirandom.data.remote

import com.mosz.wikirandom.BuildConfig
import com.mosz.wikirandom.data.model.RandomArticleResponse
import retrofit2.Response
import retrofit2.http.GET

interface RandomArticleService {
    @GET(BuildConfig.API_RANDOM_ARTICLE_ENDPOINT)
    suspend fun getRandomArticle(): Response<RandomArticleResponse>
}