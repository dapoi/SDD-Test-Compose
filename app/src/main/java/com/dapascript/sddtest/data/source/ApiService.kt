package com.dapascript.sddtest.data.source

import retrofit2.http.GET

interface ApiService {

    @GET("promos")
    suspend fun getPromos(): List<PromoResponse>
}