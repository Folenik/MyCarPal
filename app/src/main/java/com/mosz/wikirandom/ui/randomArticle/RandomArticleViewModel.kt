package com.mosz.wikirandom.ui.randomArticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosz.wikirandom.data.Repository
import com.mosz.wikirandom.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@HiltViewModel
class RandomArticleViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private var _randomArticle = MutableStateFlow<RandomArticleState>(RandomArticleState.Loading)

    val randomArticle: StateFlow<RandomArticleState> = _randomArticle.asStateFlow()

    init {
        getRandomArticle()
    }

    fun getRandomArticle() {
        viewModelScope.launch {
            repository.getRandomArticle().collect { values ->
                when(values) {
                    is NetworkResult.Success -> {
                        Timber.d("Fetch successful: ${values.data!!}")
                        _randomArticle.value = RandomArticleState.Success(values.data!!)
                    }
                    is NetworkResult.Error -> {
                        Timber.d("Fetch error")
                        _randomArticle.value = RandomArticleState.Error(values.message!!)
                    }
                }
            }
        }
    }
}

