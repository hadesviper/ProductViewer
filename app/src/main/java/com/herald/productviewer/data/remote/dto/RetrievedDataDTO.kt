package com.herald.productviewer.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.herald.productviewer.domain.models.ProductsModel

data class RetrievedDataDTO(
    @SerializedName("Product")
    val product: Product = Product(),
    @SerializedName("ProductMerchants")
    val productMerchants: List<ProductMerchant> = listOf()
) {
    data class Product(
        @SerializedName("description")
        val description: String = "",
        @SerializedName("id")
        val id: String = "",
        @SerializedName("image_url")
        val imageUrl: String = "",
        @SerializedName("name")
        val name: String = "",
        @SerializedName("price")
        val price: String = "",
        @SerializedName("product_type_id")
        val productTypeId: Any? = null,
        @SerializedName("shopping_cart_item_id")
        val shoppingCartItemId: Any? = null,
        @SerializedName("shopping_list_item_id")
        val shoppingListItemId: Any? = null,
        @SerializedName("unit_price")
        val unitPrice: Any? = null
    )

    data class ProductMerchant(
        @SerializedName("Merchant")
        val merchant: Merchant = Merchant(),
        @SerializedName("MerchantProduct")
        val merchantProduct: MerchantProduct = MerchantProduct(),
        @SerializedName("ProductMerchant")
        val productMerchant: ProductMerchant = ProductMerchant()
    ) {
        data class Merchant(
            @SerializedName("id")
            val id: String = "",
            @SerializedName("name")
            val name: String = ""
        )

        data class MerchantProduct(
            @SerializedName("buy_url")
            val buyUrl: String = "",
            @SerializedName("discount_percent")
            val discountPercent: String = "",
            @SerializedName("id")
            val id: String = "",
            @SerializedName("price")
            val price: String = "",
            @SerializedName("sku")
            val sku: String = "",
            @SerializedName("unit_price")
            val unitPrice: Any? = null,
            @SerializedName("upc")
            val upc: String = ""
        )

        data class ProductMerchant(
            @SerializedName("created")
            val created: String = "",
            @SerializedName("id")
            val id: String = "",
            @SerializedName("modified")
            val modified: String = "",
            @SerializedName("multiple_products_per_page")
            val multipleProductsPerPage: String = "",
            @SerializedName("product_id")
            val productId: String = "",
            @SerializedName("sku")
            val sku: String = "",
            @SerializedName("upc")
            val upc: String = ""
        )
    }
    fun toProductsModel(): ProductsModel {
        return ProductsModel(
            image = product.imageUrl,
            title = product.name,
            description = product.description,
            price = product.price
        )
    }
}