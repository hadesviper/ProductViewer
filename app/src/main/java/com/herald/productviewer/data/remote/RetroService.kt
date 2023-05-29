package com.herald.productviewer.data.remote

import com.herald.productviewer.data.remote.dto.RetrievedDataDTO
import retrofit2.http.GET

interface RetroService {
    @GET("wp-content/uploads/2012/09/featured.txt")
    suspend fun getProductList(): List<RetrievedDataDTO>
}