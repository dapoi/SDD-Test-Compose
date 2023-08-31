package com.dapascript.sddtest.di

import com.dapascript.sddtest.data.repository.PromoRepository
import com.dapascript.sddtest.data.repository.PromoRepositoryImpl
import com.dapascript.sddtest.data.source.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://content.digi46.id/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create API service
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providePromoRepository(apiService: ApiService): PromoRepository {
        return PromoRepositoryImpl(apiService)
    }
}