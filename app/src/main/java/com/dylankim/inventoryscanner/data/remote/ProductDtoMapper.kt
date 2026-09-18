package com.dylankim.inventoryscanner.data.remote

import com.dylankim.inventoryscanner.data.local.entity.Product

fun ProductDto.toEntity(companyId: Long): Product {
    return Product(
        companyId = companyId,
        productCode = id.toString(),
        barcode = meta.barcode,
        name = title,
        price = price.toString()
    )
}