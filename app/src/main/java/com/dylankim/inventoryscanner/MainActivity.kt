package com.dylankim.inventoryscanner

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.dylankim.inventoryscanner.data.local.InventoryDatabase
import com.dylankim.inventoryscanner.data.local.entity.Company
import com.dylankim.inventoryscanner.data.local.entity.InventoryRecord
import com.dylankim.inventoryscanner.data.local.entity.Location
import com.dylankim.inventoryscanner.data.local.entity.Product
import com.dylankim.inventoryscanner.ui.theme.InventoryScannerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = InventoryDatabase.getDatabase(applicationContext)

        lifecycleScope.launch {
            val count = database.companyDao().getCount()

            //초기 셋팅 - DB Count 후 0 일 경우만
            if (count == 0) {
                database.companyDao().insertAll(
                    listOf(
                        Company(name = "업체A"),
                        Company(name = "업체B")
                    )
                )
                val companies = database.companyDao().getAll()

                //A업체 - 매장,창고
                database.locationDao().insert(
                    Location(
                        companyId = companies[0].id,
                        name = "매장"
                    )
                )
                database.locationDao().insert(
                    Location(
                        companyId = companies[0].id,
                        name = "창고"
                    )
                )
                //B업체 - 매장,창고,외부창고
                database.locationDao().insert(
                    Location(
                        companyId = companies[1].id,
                        name = "매장"
                    )
                )
                database.locationDao().insert(
                    Location(
                        companyId = companies[1].id,
                        name = "창고"
                    )
                )
                database.locationDao().insert(
                    Location(
                        companyId = companies[1].id,
                        name = "외부창고"
                    )
                )

                Log.d("RoomTest", "companies = $companies")

                val companyALocations =
                    database.locationDao().getLocationsByCompanyId(companies[0].id)

                val companyBLocations =
                    database.locationDao().getLocationsByCompanyId(companies[1].id)

                Log.d("RoomTest", "업체A locations = $companyALocations")
                Log.d("RoomTest", "업체B locations = $companyBLocations")

                database.productDao().insert(
                    Product(
                        companyId = companies[0].id ,
                        productCode = "A001",
                        barcode = "88001",
                        name = "A업체 상품1",
                        price = "1000"
                    )
                )
                database.productDao().insert(
                    Product(
                        companyId = companies[0].id ,
                        productCode = "A002",
                        barcode = "88002",
                        name = "A업체 상품2",
                        price = "2000"
                    )
                )


                database.productDao().insert(
                    Product(
                        companyId = companies[0].id ,
                        productCode = "A003",
                        barcode = "88003",
                        name = "A업체 상품3",
                        price = "3000"
                    )
                )

                val product =
                    database.productDao().getProductByBarcode("88003")

                Log.d("RoomTest", "product = $product")

                database.inventoryRecordDao().insert(
                    InventoryRecord(
                        locationId = companyALocations[0].id,
                        locationNumber = "001",
                        countingNumber = 1,
                        productCode = "A002",
                        barcode = "88002",
                        productName = "A업체 상품2",
                        price = "2000",
                        quantity = "1",
                        createdAt = "2026-08-25 15:00:00"
                    )
                )

                database.inventoryRecordDao().insert(
                    InventoryRecord(
                        locationId = companyALocations[1].id,
                        locationNumber = "001",
                        countingNumber = 1,
                        productCode = "A001",
                        barcode = "88001",
                        productName = "A업체 상품1",
                        price = "1000",
                        quantity = "1.5",
                        createdAt = "2026-08-25 15:10:00"
                    )
                )

                val inventoryRecord =
                    database.inventoryRecordDao().getAll()

                Log.d("RoomTest", "inventoryRecord = $inventoryRecord")
            }


            val inventoryRecord =
                database.inventoryRecordDao().getAll()

            database.inventoryRecordDao().deleteAll()

            val inventoryRecordAfterDelete =
                database.inventoryRecordDao().getAll()

            Log.d("RoomTest", "inventoryRecord = $inventoryRecord \r inventoryRecordAfterDelete = $inventoryRecordAfterDelete")

        }

        enableEdgeToEdge()
        setContent {
            InventoryScannerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InventoryScannerTheme {
        Greeting("Android")
    }
}