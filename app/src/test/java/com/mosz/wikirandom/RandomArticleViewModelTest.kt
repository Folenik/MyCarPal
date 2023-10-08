package com.mosz.wikirandom

import app.cash.turbine.test
import com.mosz.wikirandom.data.Repository
import com.mosz.wikirandom.ui.randomArticle.RandomArticleState
import com.mosz.wikirandom.ui.randomArticle.RandomArticleViewModel
import com.mosz.wikirandom.utils.MainDispatcherRule
import com.mosz.wikirandom.utils.MockData
import com.mosz.wikirandom.utils.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class RandomArticleViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository: Repository = mock()
    private val viewModel by lazy {
        RandomArticleViewModel(
            repository,
            mainDispatcherRule.testDispatcherProvider
        )
    }

    @Test
    fun `should try to get random article when initialized and return success`() = runTest {
        whenever(repository.getRandomArticle()).thenReturn(
            flow {
                emit(NetworkResult.Success(MockData.getRandomArticle()))
            }
        )

        viewModel.randomArticle.test {
            assertEquals(RandomArticleState.Success::class, awaitItem()::class)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `should try to get random article when initialized and return error`() = runTest {
        whenever(repository.getRandomArticle()).thenReturn(
            flow {
                emit(MockData.getErrorResult())
            }
        )

        viewModel.randomArticle.test {
            assertEquals(RandomArticleState.Error::class, awaitItem()::class)
            cancelAndConsumeRemainingEvents()
        }
    }
}