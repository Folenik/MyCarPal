package com.mosz.wikirandom.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.mosz.wikirandom.R
import com.mosz.wikirandom.data.model.RandomArticleResponse
import com.mosz.wikirandom.ui.randomArticle.RandomArticleViewModel

@Composable
fun RandomArticleBox(
    viewModel: RandomArticleViewModel, randomArticle: RandomArticleResponse
) {
    val lightGreenColor = colorResource(id = R.color.lightGreen)
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = randomArticle.thumbnail?.source!!)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
            }).build()
    )
    val isImageLoading = painter.state is AsyncImagePainter.State.Loading

    Box(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = lightGreenColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = randomArticle.title!!,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .alpha(if (isImageLoading) 0f else 1f)
                        .testTag("header")
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                ) {
                    Image(
                        painter = painter,
                        contentDescription = stringResource(R.string.image),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(lightGreenColor)
                    )
                    if (isImageLoading) {
                        ProgressIndicator(modifier = Modifier.alpha(1f))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = randomArticle.extract!!,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(if (isImageLoading) 0f else 1f)
                        .testTag("description")
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            RefreshButton { viewModel.getRandomArticle() }
        }
    }
}
