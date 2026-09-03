package com.dylankim.inventoryscanner.data.local.entity

import androidx.room3.ColumnInfo
import androidx.room3.Entity
import androidx.room3.ForeignKey
import androidx.room3.PrimaryKey

@Entity(
    tableName = "InventoryRecord",
    foreignKeys = [
        ForeignKey(
            entity = Location::class,
            parentColumns = ["id"],
            childColumns = ["locationId"]
        )
    ]
)
data class InventoryRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val locationId: Long,
    val locationNumber: String,
    val countingNumber: Int,
    val barcode: String,
    val productCode: String,
    val productName: String,
    val price: String,
    val quantity: String,
    val createdAt: String,
    @ColumnInfo(defaultValue = "")
    val updatedAt: String
)