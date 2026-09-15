package com.dylankim.inventoryscanner.data.export

import com.dylankim.inventoryscanner.data.local.dto.InventoryCsvRow

object CsvGenerator {

    fun generate(rows: List<InventoryCsvRow>): String {
        val header = "바코드,상품명,수량,단가,조사순번,로케이션명,로케이션 넘버,조사시간"

        val data = rows.joinToString("\n") { row ->
            listOf(
                row.barcode,
                row.productName,
                row.quantity,
                row.price,
                row.countingNumber,
                row.locationName,
                row.locationNumber,
                row.createdAt
            ).joinToString(",") { value ->
                escape(value.toString())
            }
        }

        return "$header\n$data"
    }

    private fun escape(value: String): String {
        return if (
            value.contains(",") ||
            value.contains("\"") ||
            value.contains("\n") ||
            value.contains("\r")
        ) {
            "\"${value.replace("\"", "\"\"")}\""
        } else {
            value
        }
    }
}