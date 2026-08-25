package com.dylankim.inventoryscanner.data.local

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.dylankim.inventoryscanner.data.local.dao.CompanyDao
import com.dylankim.inventoryscanner.data.local.dao.InventoryRecordDao
import com.dylankim.inventoryscanner.data.local.dao.LocationDao
import com.dylankim.inventoryscanner.data.local.dao.ProductDao
import com.dylankim.inventoryscanner.data.local.entity.Company
import com.dylankim.inventoryscanner.data.local.entity.InventoryRecord
import com.dylankim.inventoryscanner.data.local.entity.Location
import com.dylankim.inventoryscanner.data.local.entity.Product

@Database(
    entities = [
        Company::class,
        Location::class,
        Product::class,
        InventoryRecord::class
    ],
    version = 1
)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun companyDao(): CompanyDao
    abstract fun locationDao(): LocationDao
    abstract fun productDao(): ProductDao
    abstract fun inventoryRecordDao(): InventoryRecordDao

    companion object {
        private var INSTANCE: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                InventoryDatabase::class.java,
                "inventory_database"
            ).build().also {
                INSTANCE = it
            }
        }
    }
}

