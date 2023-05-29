package com.herald.productviewer.data.remote.repository

import com.herald.productviewer.data.remote.RetroService
import com.herald.productviewer.data.remote.dto.RetrievedDataDTO
import com.herald.productviewer.domain.repository.RetroRepo
import javax.inject.Inject

class RetroImpl @Inject constructor(
    private val retroService: RetroService
):RetroRepo {
    override suspend fun getProductList(): List<RetrievedDataDTO> {
        return retroService.getProductList()
    }
}