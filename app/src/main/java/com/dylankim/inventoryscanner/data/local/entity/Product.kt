package com.dylankim.inventoryscanner.data.local.entity

import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.Index
import androidx.room3.PrimaryKey

@Entity(
    tableName = "Product",
    indices = [
        Index(value = ["barcode"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = Company::class,
            parentColumns = ["id"],
            childColumns = ["companyId"]
        )
    ]
)
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val companyId: Long,
    val productCode: String,
    val barcode: String,
    val name: String,
    val price: String
)