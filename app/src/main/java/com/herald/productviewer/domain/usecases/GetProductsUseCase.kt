package com.herald.productviewer.domain.usecases

import com.herald.productviewer.common.Resources
import com.herald.productviewer.domain.models.ProductsModel
import com.herald.productviewer.domain.repository.RetroRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val retroRepo: RetroRepo
) {
    operator fun invoke(): Flow<Resources<List<ProductsModel>>> = flow {
        try {
            emit(Resources.Loading())
            val data = retroRepo.getProductList()
            emit(Resources.Success(data.map {
                it.toProductsModel()
            }))
        } catch (e: HttpException) {
            emit(Resources.Error(message = e.message.toString()))
        } catch (e: IOException) {
            emit(Resources.Error(message = e.message.toString()))
        }
    }
}