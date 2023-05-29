package com.herald.productviewer.domain.repository

import com.herald.productviewer.data.remote.dto.RetrievedDataDTO

interface RetroRepo {
    suspend fun getProductList(): List<RetrievedDataDTO>
}