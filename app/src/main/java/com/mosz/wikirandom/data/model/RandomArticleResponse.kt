package com.mosz.wikirandom.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomArticleResponse(
    @SerialName("title") var title: String? = null,
    @SerialName("thumbnail") var thumbnail: Thumbnail? = Thumbnail(),
    @SerialName("extract") var extract: String? = null
) {
    @Serializable
    data class Thumbnail(
        @SerialName("source") var source: String? = null
    )
}