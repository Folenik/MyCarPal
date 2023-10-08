package com.mosz.wikirandom.utils

import com.mosz.wikirandom.data.model.RandomArticleResponse

object MockData {
    private const val errorMessage = "Something went wrong.."
    private const val randomArticleTitle = "Super Mario"
    private const val randomArticleThumbnailSource =
        "https://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Mario_Series_Logo.svg/1920px-Mario_Series_Logo.svg.png"
    private const val randomArticleExtract = "Super Mario is a platform game series created " +
            "by Nintendo starring their mascot, Mario. " +
            "It is the central series of the greater Mario franchise. " +
            "At least one Super Mario game has been released for " +
            "every major Nintendo video game console. " +
            "There are more than 20 games in the series."

    fun getRandomArticle() = RandomArticleResponse(
        title = randomArticleTitle,
        thumbnail = RandomArticleResponse.Thumbnail(
            source = randomArticleThumbnailSource
        ),
        extract = randomArticleExtract
    )

    fun getErrorResult() = NetworkResult.Error<RandomArticleResponse>(errorMessage)
}
