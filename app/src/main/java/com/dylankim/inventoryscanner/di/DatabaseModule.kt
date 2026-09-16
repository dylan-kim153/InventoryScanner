package com.dylankim.inventoryscanner.di

import android.content.Context
import com.dylankim.inventoryscanner.data.local.InventoryDatabase
import com.dylankim.inventoryscanner.data.local.dao.CompanyDao
import com.dylankim.inventoryscanner.data.local.dao.InventoryRecordDao
import com.dylankim.inventoryscanner.data.local.dao.LocationDao
import com.dylankim.inventoryscanner.data.local.dao.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : InventoryDatabase {
        return InventoryDatabase.getDatabase(context)
    }

    @Provides
    fun provideCompanyDao(
        database: InventoryDatabase
    ): CompanyDao {
        return database.companyDao()
    }

    @Provides
    fun provideLocationDao(
        database: InventoryDatabase
    ): LocationDao {
        return database.locationDao()
    }

    @Provides
    fun provideProductDao(
        database: InventoryDatabase
    ): ProductDao {
        return database.productDao()
    }

    @Provides
    fun provideInventoryRecordDao(
        database: InventoryDatabase
    ): InventoryRecordDao {
        return database.inventoryRecordDao()
    }
}