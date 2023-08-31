package com.dapascript.sddtest.data.repository

import com.dapascript.sddtest.data.source.ApiService
import com.dapascript.sddtest.data.source.PromoResponse
import com.dapascript.sddtest.vo.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PromoRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PromoRepository {

    override fun getPromos(): Flow<Resource<List<PromoResponse>>> {
        return flow {
            emit(Resource.Loading)

            try {
                val response = apiService.getPromos()
                emit(Resource.Success(response))
            } catch (e: Throwable) {
                emit(Resource.Error(Throwable()))
            }
        }.flowOn(Dispatchers.IO)
    }
}

interface PromoRepository {
    fun getPromos(): Flow<Resource<List<PromoResponse>>>
}