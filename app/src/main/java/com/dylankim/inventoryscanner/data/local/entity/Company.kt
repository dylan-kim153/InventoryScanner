package com.dylankim.inventoryscanner.data.local.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "company")
data class Company(
    @PrimaryKey(true)
    val id: Long = 0,
    val name: String
)




