package com.mosz.wikirandom.data

import com.mosz.wikirandom.data.model.BaseResponse
import com.mosz.wikirandom.data.model.RandomArticleResponse
import com.mosz.wikirandom.data.remote.RemoteDataSource
import com.mosz.wikirandom.utils.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseResponse() {

    suspend fun getRandomArticle(): Flow<NetworkResult<RandomArticleResponse>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getRandomArticle() })
        }.flowOn(Dispatchers.IO)
    }
}